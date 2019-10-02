/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.captcha.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Properties;

import org.zcoreframework.captcha.BackgroundProducer;
import org.zcoreframework.captcha.GimpyEngine;
import org.zcoreframework.captcha.Producer;
import org.zcoreframework.captcha.text.TextProducer;
import org.zcoreframework.captcha.text.WordRenderer;
import org.zcoreframework.captcha.util.Config;
import org.zcoreframework.captcha.util.Configurable;

/**
 * Default {@link Producer} implementation which draws a captcha image using
 * {@link WordRenderer}, {@link GimpyEngine}, {@link BackgroundProducer}. Text
 * creation uses {@link TextProducer}.
 */
public class DefaultCaptcha extends Configurable implements Producer {

	public DefaultCaptcha() {
		Properties properties = new Properties();
		properties.setProperty("captcha.textproducer.font.color", "0,0,0");
		properties.setProperty("captcha.noise.impl", "org.zcoreframework.captcha.impl.NoNoise");
		properties.setProperty("captcha.noise.color", "0,0,0");
		properties.setProperty("captcha.border.color", "white");
		properties.setProperty("captcha.background.color.from", "white");
		properties.setProperty("captcha.background.color.to", "white");
		this.setConfig(new Config(properties));
	}

	public DefaultCaptcha(Config config) {
		this.setConfig(config);
	}

	/**
	 * Create an image which will have written a distorted text.
	 * 
	 * @param text
	 *            the distorted characters
	 * @return image with the text
	 */
	public BufferedImage createImage(String text, int width, int height) {
		WordRenderer wordRenderer = getConfig().getWordRendererImpl();
		GimpyEngine gimpyEngine = getConfig().getObscurificatorImpl();
		BackgroundProducer backgroundProducer = getConfig().getBackgroundImpl();
		boolean isBorderDrawn = getConfig().isBorderDrawn();

		BufferedImage bi = wordRenderer.renderWord(text, width, height);
		bi = gimpyEngine.getDistortedImage(bi);
		bi = backgroundProducer.addBackground(bi);
		Graphics2D graphics = bi.createGraphics();
		if (isBorderDrawn) {
			drawBox(graphics, width, height);
		}
		return bi;
	}

	private void drawBox(Graphics2D graphics, int width, int height) {
		Color borderColor = getConfig().getBorderColor();
		int borderThickness = getConfig().getBorderThickness();

		graphics.setColor(borderColor);

		if (borderThickness != 1) {
			BasicStroke stroke = new BasicStroke((float) borderThickness);
			graphics.setStroke(stroke);
		}

		Line2D line1 = new Line2D.Double(0, 0, 0, width);
		graphics.draw(line1);
		Line2D line2 = new Line2D.Double(0, 0, width, 0);
		graphics.draw(line2);
		line2 = new Line2D.Double(0, height - 1, width, height - 1);
		graphics.draw(line2);
		line2 = new Line2D.Double(width - 1, height - 1, width - 1, 0);
		graphics.draw(line2);
	}

	/**
	 * @return the text to be drawn
	 */
	public String createText() {
		return getConfig().getTextProducerImpl().getText();
	}

}
