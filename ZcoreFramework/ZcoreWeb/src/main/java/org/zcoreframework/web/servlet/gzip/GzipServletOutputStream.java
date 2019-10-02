/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.gzip;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;

public class GzipServletOutputStream extends ServletOutputStream {
		
	protected GZIPOutputStream gzipOutputStream;
	
	public GzipServletOutputStream(OutputStream outputStream) throws IOException {
		super();
		gzipOutputStream = new GZIPOutputStream(outputStream);
	}
	
	@Override
	public void write(int b) throws IOException {
		gzipOutputStream.write((byte) b);
	}

	@Override
	public void write(byte b[]) throws IOException {
		gzipOutputStream.write(b, 0, b.length);
	}

	@Override
	public void write(byte b[], int off, int len) throws IOException {
		gzipOutputStream.write(b, off, len);
	}	
	
	@Override
	public void flush() throws IOException {
		gzipOutputStream.flush();
	}

	@Override
	public void close() throws IOException {
		gzipOutputStream.finish();		
	}

}
