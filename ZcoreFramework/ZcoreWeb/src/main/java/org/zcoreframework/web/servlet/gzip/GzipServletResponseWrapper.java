/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.gzip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipServletResponseWrapper extends HttpServletResponseWrapper {
	
	protected ServletOutputStream outputStream;
	
	protected PrintWriter printWriter;

	public GzipServletResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	public ServletOutputStream createOutputStream() throws IOException {
		return (new GzipServletOutputStream(getResponse().getOutputStream()));
	}

	public void close() throws IOException {
		if (printWriter != null) {
			printWriter.close();
		}
		if (outputStream != null) {
			outputStream.close();
		}
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (printWriter != null) 
			throw new IllegalStateException("getWriter() has already been called!");
		if (outputStream == null)
			outputStream = createOutputStream();
		return outputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (printWriter != null) 
			return printWriter;
		if (outputStream != null) 
			throw new IllegalStateException("getOutputStream() has already been called!");
		outputStream = createOutputStream();
		printWriter = new PrintWriter(new OutputStreamWriter(outputStream, getResponse().getCharacterEncoding()));
		return printWriter;
	}
		
	@Override
	public void flushBuffer() throws IOException {
		if (outputStream != null)
			outputStream.flush();		
		super.flushBuffer();
	}	
	
}
