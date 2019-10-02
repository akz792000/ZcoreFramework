/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.captcha.impl;

import java.awt.image.BufferedImage;

import org.zcoreframework.captcha.NoiseProducer;
import org.zcoreframework.captcha.util.Configurable;

/**
 * Imlemention of NoiseProducer that does nothing.
 * 
 * @author Yuxing Wang
 */
public class NoNoise extends Configurable implements NoiseProducer {
	/**
	 */
	public void makeNoise(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour) {
		//Do nothing.
	}
}
