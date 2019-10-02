/**
 *
 * @author Ali Karimizandi
 * @since 2009
 *
 */

package org.zcoreframework.component.captcha;

import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.component.AjaxClientable;
import org.zcoreframework.base.component.Component;
import org.zcoreframework.base.component.ResponseResult;
import org.zcoreframework.base.component.ResponseResult.ResponseType;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.captcha.Producer;
import org.zcoreframework.captcha.impl.DefaultCaptcha;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import static java.util.Optional.ofNullable;

public class Captcha extends AbstractComponentImpl implements AjaxClientable {

	private int height;

	private int width;

	private String url;

	private String value;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	protected String getSessionKey() throws IOException {
		// get servlet request attributes
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		// get image
		HttpTemplate template = new HttpTemplate(servletRequestAttributes.getRequest(), servletRequestAttributes.getResponse());
		template.setMediaType(MediaType.TEXT_HTML);
		return ofNullable(template.execute(url, HttpMethod.GET, String.class)).orElse("");
	}

	protected void render(OutputStream outputStream) throws IOException {
		Producer producer = new DefaultCaptcha();
		BufferedImage bi = producer.createImage(getSessionKey(), width, height);
		Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream);
		ImageIO.write(bi, "jpeg", base64OutputStream);
		/*
		 * Don't forget to close the stream when the writing operation is
		 * completed. This is necessary to finalize the encoded data line.
		 */
		base64OutputStream.close();
	}

	@Override
	public Object parseValue(String text) {
		return text;
	}

	@Override
	public void bindValue(Object object) {
		setValue((String) object);
	}

	@Override
	public Object data() {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			render(byteArrayOutputStream);
		} catch (IOException e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}
		return byteArrayOutputStream.toString();
	}

	@Override
	public Object value() {
		return null;
	}

	@Override
	public ResponseResult partial() throws BaseException {
		return new ResponseResult(ResponseType.BYTE, data());
	}

	@Override
	public Component getClientModel() {
		return null;
	}

}