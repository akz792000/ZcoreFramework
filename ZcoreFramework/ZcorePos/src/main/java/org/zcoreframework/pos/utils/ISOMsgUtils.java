package org.zcoreframework.pos.utils;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOField;
import org.jpos.iso.ISOMsg;
import org.zcoreframework.pos.data.ISOMessage;
import org.zcoreframework.pos.exception.PosMessageFormatException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

/**
 *
 */
@SuppressWarnings("rawtypes")
public final class ISOMsgUtils {

    public static void result(ISOMsg inMsg, String result) {
        inMsg.set(ISOMessage.ACTION_CODE, result);
    }

    public static ISOMessage convert(ISOMsg inMsg) {
        if (inMsg == null) {
            return null;
        }

        Map map = inMsg.getChildren();
        ISOMessage message = new ISOMessage();

        for (Object entryValue : map.values()) {
            if (!(entryValue instanceof ISOField)) {
                continue;
            }

            ISOField field = (ISOField) entryValue;
            message.put(field.getFieldNumber(), field.getValue());
        }

        return message;
    }

    public static ISOMsg merge(ISOMsg inMsg, ISOMessage zMsg) {
        if (inMsg == null) {
            return null;
        } else if (zMsg == null) {
            return inMsg;
        }

        for (Map.Entry<Integer, Object> entry : zMsg.entrySet()) {
            if (!inMsg.hasField(entry.getKey()) && entry.getValue() != null) {
                inMsg.set(entry.getKey(), entry.getValue().toString());
            }
        }

        return inMsg;
    }

    public static ISOMsg response(ISOMsg msg) {
        try {
            if (msg.isRequest()) {
                msg.setResponseMTI();
            }
            return msg;
        } catch (ISOException e) {
            throw new PosMessageFormatException(e);
        }
    }

    public static String parse(ISOMsg msg) {
        ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baoStream);

        msg.dump(printStream, "");
        printStream.flush();

        return new String(baoStream.toByteArray());
    }
}
