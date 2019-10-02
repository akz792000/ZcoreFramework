/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.memory;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceNotActiveException;
import com.hazelcast.core.IAtomicReference;
import com.hazelcast.core.ILock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.component.map.GenericMap;

import java.io.IOException;
import java.lang.reflect.Field;

public class ClientJointMemoryImpl implements JointMemory {

    private static final Log log = LogFactory.getLog(ClientJointMemoryImpl.class);
    private final ClientConfig config;
    private final Object lock = new Object();
    private HazelcastInstance jointMemory;

    public ClientJointMemoryImpl() {
        // init configuration
        try {
            config = new XmlClientConfigBuilder("META-INF/hazelcast.xml").build();
        } catch (IOException e) {
            throw new RuntimeException("Config file doesn't exist.");
        }

        // init joint memory
        initJointMemory();
    }

    protected void writeMemoryData() {
        ApplicationContextUtils.getApplicationContext().getBeansOfType(MemoryAware.class).values().forEach(bean -> {
            for (Field field : bean.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    GenericMap<?> value = (GenericMap<?>) field.get(bean);
                    Memory memory = field.getAnnotation(Memory.class);
                    if (memory != null) {
                        write(memory.value(), value.getItem());
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    ReflectionUtils.handleReflectionException(e);
                }
            }
        });
    }

    private boolean initJointMemory() {
        try {
            // new hazelcast client
            jointMemory = HazelcastClient.newHazelcastClient(config);

            // write memory data
            writeMemoryData();

            // log
            if (log.isDebugEnabled()) {
                log.debug("joint memory client create.");
            }

            return true;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("client cann't connect to server, this will happen in the next read try.");
                log.debug("Error: " + e.getMessage());
            }
        }
        return false;
    }

    public Object read(String key) {
        try {
            if (jointMemory == null) {
                if (!initJointMemory()) {
                    throw new RuntimeException("Couldn't connect to server.");
                }
            }
            return jointMemory.getAtomicReference(key).get();
        } catch (HazelcastInstanceNotActiveException exp) {
            /*
             * the master downs then client try to connect it and after specific
			 * times, client Could not re-connect to cluster and shutting down
			 * itself
			 */

            // log
            if (log.isDebugEnabled()) {
                log.debug("hazelcast instance not active, new instance will be created.");
            }

            // init
            synchronized (lock) {
                if (!jointMemory.getLifecycleService().isRunning()) {
                    initJointMemory();
                }
            }

            // read
            return read(key);
        }
    }

    protected void doWrite(String key, Object value, boolean rewrite) {
        ILock lock = jointMemory.getLock(key + ".Lock");
        if (lock.tryLock()) {
            try {
                IAtomicReference<?> reference = jointMemory.getAtomicReference(key);
                if (rewrite || reference.isNull()) {
                    jointMemory.getAtomicReference(key).set(value);
                }
            } finally {
                lock.unlock();
                lock.destroy();
            }
        }
    }

    @Override
    public void write(String key, Object value) {
        doWrite(key, value, false);
    }

    @Override
    public void update(String key, Object value) {
        doWrite(key, value, true);
    }

}
