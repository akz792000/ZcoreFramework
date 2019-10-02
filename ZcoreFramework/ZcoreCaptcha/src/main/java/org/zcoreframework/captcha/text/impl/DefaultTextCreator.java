/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.captcha.text.impl;

import java.util.Random;

import org.zcoreframework.captcha.text.TextProducer;
import org.zcoreframework.captcha.util.Configurable;

/**
 * {@link DefaultTextCreator} creates random text from an array of characters
 * with specified length.
 */
public class DefaultTextCreator extends Configurable implements TextProducer {
	
	/**
	 * @return the random text
	 */
	public String getText() {
		int length = getConfig().getTextProducerCharLength();
		char[] chars = getConfig().getTextProducerCharString();
		Random rand = new Random();
		StringBuffer text = new StringBuffer();
		for (int i = 0; i < length; i++) {
			text.append(chars[rand.nextInt(chars.length)]);
		}
		return text.toString();
	}
	
}
