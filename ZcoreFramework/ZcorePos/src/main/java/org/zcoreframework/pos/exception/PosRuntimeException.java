package org.zcoreframework.pos.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.zcoreframework.base.util.MessageSourceUtils;

/**
 *
 */
public class PosRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PosRuntimeException() {
        super();
    }

    public PosRuntimeException(Throwable cause) {
        super(cause);
    }

    public PosRuntimeException(String message) {
        super(getMsg(message));
    }

    public PosRuntimeException(String message, Throwable cause) {
        super(getMsg(message), cause);
    }

    private static String getMsg(String message) {
        if (MessageSourceUtils.getMessageSource() == null) {
            return message;
        }

        return MessageSourceUtils.getMessageSource().getMessage(message, null, message, LocaleContextHolder.getLocale());
    }

}
