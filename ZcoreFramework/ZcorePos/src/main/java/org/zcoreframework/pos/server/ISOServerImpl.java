package org.zcoreframework.pos.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jpos.core.SimpleConfiguration;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.XMLChannel;
import org.jpos.iso.packager.XMLPackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.zcoreframework.pos.exception.PosRuntimeException;
import org.zcoreframework.pos.service.ISOServiceDispatcher;

/**
 *
 */
@SuppressWarnings("deprecation")
public class ISOServerImpl implements InitializingBean, DisposableBean {

    private final Log srvLogger = LogFactory.getLog(ISOServerImpl.class);

    private final String name;

    private final int port;

    private final int maxSessions;

    private final int minSessions;

    protected ISOServer server;

    private ISOServiceDispatcher dispatcher;

    public ISOServerImpl(String name, int port, int minSessions, int maxSessions) {
        this.name = name;
        this.port = port;
        this.minSessions = minSessions;
        this.maxSessions = maxSessions;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.port <= 0) {
            throw new PosRuntimeException("TODO");
        } else if (this.maxSessions < 1 || this.minSessions < 1) {
            throw new PosRuntimeException("TODO");
        } else if (this.maxSessions < this.minSessions) {
            throw new PosRuntimeException("TODO");
        }

        Logger logger = new Logger();
        logger.addListener(new ISOLogListener(srvLogger));

        ServerChannel channel = new XMLChannel(new XMLPackager());
        ((LogSource) channel).setLogger(logger, "zchannel");

        this.server = new ISOServer(this.port, channel
                , new org.jpos.util.ThreadPool(this.minSessions, this.maxSessions, this.name + "-ThreadPool"));

        this.server.setConfiguration(new SimpleConfiguration());
        this.server.setLogger(logger, "zcore");
        this.server.addISORequestListener((src, msg) -> {
            dispatcher.dispatch(src, msg);
            return true;
        });

        new Thread(this.server).start();
    }

    @Override
    public void destroy() throws Exception {
        if (this.server != null) {
            this.server.shutdown();
        }
    }

    public ISOServiceDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(ISOServiceDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public int getMaxSessions() {
        return maxSessions;
    }

    public int getMinSessions() {
        return minSessions;
    }
}
