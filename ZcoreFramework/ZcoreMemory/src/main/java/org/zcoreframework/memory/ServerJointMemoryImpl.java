/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.memory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicReference;
import com.hazelcast.core.ILock;

public class ServerJointMemoryImpl implements JointMemory {

	private static final Log log = LogFactory.getLog(ServerJointMemoryImpl.class);

	private HazelcastInstance jointMemory;
	
	private final Config config;

	public ServerJointMemoryImpl() {		
		// set config
		config = new ClasspathXmlConfig("META-INF/hazelcast.xml");

		// set instance
		jointMemory = Hazelcast.newHazelcastInstance(config);

		// log
		if (log.isDebugEnabled()) {
			log.debug("joint memory server create.");
		}
	}

	@Override
	public Object read(String key) {
		return jointMemory.getAtomicReference(key).get();		
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
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(String key, Object value) {
		throw new UnsupportedOperationException();
	}

}
