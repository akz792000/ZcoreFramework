/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.http;

/*
 * for preventing of UnknownHttpStatusCodeException the value we 
 * have used as status code should have existed in HttpStatus class
 */
public class ResponseStatusCode {

	public static final int OK = 200;

	public static final int NO_CONTENT = 204;

	/*
	 * for get rid of this error java cannot retry due to proxy authentication,
	 * in streaming mode change the 401 to 451
	 */
	public static final int UNAUTHORIZED = 451;

	public static final int FORBIDDEN = 403;

	public static final int NOT_FOUND = 404;

	public static final int AUTHENTICATION_TIMEOUT = 419;

	public static final int SECURITY_CREDENTIAL = 420;

	public static final int VALIDATE = 590;

	public static final int EXCEPTION = 591;

	public static final int UNSUPPORTED_EXCEPTION = 592;

    public static final int MODAL_EXCEPTION = 593;

	public static final int HANDLE_DEVELOPMENT_MODE = 597;

	public static final int HANDLE_PRODUCTION_MODE = 598;

}
