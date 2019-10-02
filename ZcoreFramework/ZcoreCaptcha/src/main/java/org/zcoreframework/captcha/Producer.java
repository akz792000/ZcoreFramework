/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.captcha;

import java.awt.image.BufferedImage;

/**
 * Responsible for creating captcha image with a text drawn on it.
 */
public interface Producer {
	/**
	 * Create an image which will have written a distorted text.
	 * 
	 * @param text
	 *            the distorted characters
	 * @return image with the text
	 */
	public BufferedImage createImage(String text, int width, int height);

	/**
	 * @return the text to be drawn
	 */
	public abstract String createText();
}