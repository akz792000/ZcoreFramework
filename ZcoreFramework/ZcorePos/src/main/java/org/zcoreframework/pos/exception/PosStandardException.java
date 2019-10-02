package org.zcoreframework.pos.exception;

/**
 *
 */
public class PosStandardException extends PosRuntimeException {

    private static final long serialVersionUID = 1L;

    private final String standardCode;

    public PosStandardException(String standardCode) {
        super();
        this.standardCode = standardCode;
    }

    public PosStandardException(String standardCode, Throwable cause) {
        super(cause);
        this.standardCode = standardCode;
    }

    public PosStandardException(String message, String standardCode) {
        super(message);
        this.standardCode = standardCode;
    }

    public PosStandardException(String message, String standardCode, Throwable cause) {
        super(message, cause);
        this.standardCode = standardCode;
    }

    public String getStandardCode() {
        return standardCode;
    }
}
