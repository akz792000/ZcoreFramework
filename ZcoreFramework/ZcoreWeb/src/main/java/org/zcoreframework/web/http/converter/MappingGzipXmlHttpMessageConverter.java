/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.http.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class MappingGzipXmlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	public static final byte[] OPEN_ELEMENT_START = "<".getBytes();
	
	public static final byte[] OPEN_ELEMENT_STOP = "</".getBytes();
	
	public static final byte[] CLOSE_ELEMENT = ">".getBytes();
	
	private boolean gzipEnabled = false;
	
	public boolean isGzipEnabled() {
		return gzipEnabled;
	}

	public void setGzipEnabled(boolean gzipEnabled) {
		this.gzipEnabled = gzipEnabled;
	}

	public MappingGzipXmlHttpMessageConverter() {
		super(new MediaType("application", "xml", DEFAULT_CHARSET));
	}

	protected JavaType getJavaType(Class<?> clazz) {
		return TypeFactory.defaultInstance().constructType(clazz);
	}
	
	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return canRead(mediaType);
	}
	
	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		//return canWrite(mediaType);
		return false;
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return null;
	}	
	
	protected void writeStartElement(Object o, OutputStream outputStream) throws IOException {
        outputStream.write(OPEN_ELEMENT_START);
        outputStream.write(o.toString().getBytes());
        outputStream.write(CLOSE_ELEMENT);	
	}
	
	protected void writeStopElement(Object o, OutputStream outputStream) throws IOException {
        outputStream.write(OPEN_ELEMENT_STOP);
        outputStream.write(o.toString().getBytes());
        outputStream.write(CLOSE_ELEMENT);	
	}	
	
	protected void writeXml(Object o, OutputStream outputStream) throws IOException {
		Map<?, ?> map = (Map<?, ?>) o;
        for (Object obj : map.entrySet()) {
            Entry<?, ?> entry = (Entry<?, ?>) obj;
            writeStartElement(entry.getKey(), outputStream);
            if (entry.getValue() instanceof Map)
            	writeXml(entry.getValue(), outputStream);
            else
            	outputStream.write(entry.getValue().toString().getBytes());
            writeStopElement(entry.getKey(), outputStream);         
        }
	}	
	
	protected void writeXmlInternal(Object o, OutputStream outputStream) throws IOException {
		writeStartElement("root", outputStream);
		writeXml(o, outputStream);
		writeStopElement("root", outputStream);
	}

	protected void writeGzipXmlInternal(Object o, OutputStream outputStream)
			throws IOException, HttpMessageNotWritableException {
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
		writeXmlInternal(o, gzipOutputStream);
		gzipOutputStream.finish();
		gzipOutputStream.flush();
	}	
		
	@Override
	protected void writeInternal(Object o, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {		
		if (gzipEnabled) {
			HttpHeaders headers = outputMessage.getHeaders();
			headers.add("Content-Encoding", "gzip");
			writeGzipXmlInternal(o, outputMessage.getBody());
		} else
			writeXmlInternal(o, outputMessage.getBody());	
	}

}

