package org.zcoreframework.pos.server;

import org.apache.commons.logging.Log;
import org.jpos.util.LogEvent;
import org.jpos.util.LogListener;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 */
public class ISOLogListener implements LogListener {

    private final Log logger;

    public ISOLogListener(Log logger) {
        this.logger = logger;
    }

    @Override
    public LogEvent log(LogEvent logEvent) {
        ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baoStream);

        logEvent.dump(printStream, "");
        printStream.flush();

        logger.info("\n" + new String(baoStream.toByteArray()));

        return logEvent;
    }
}
