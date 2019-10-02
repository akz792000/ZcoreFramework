/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.zcoreframework.base.http.ResponseStatusCode;

public class ResourceRequestHandler extends ResourceHttpRequestHandler {
	
	private final static Log logger = LogFactory.getLog(ResourceRequestHandler.class);
	
	public static final String REGEX = "(\\$\\{)(.*?)(\\})";
	
	private MessageSource messageSource;
		
	public MessageSource getMessageSource() {
		return messageSource;
	}
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (messageSource != null) {
			
			// For very general mappings (e.g. "/") we need to check 404 first
			Resource resource = getResource(request);
			if (resource == null) {
				logger.trace("No matching resource found - returning 404");
				response.sendError(ResponseStatusCode.NOT_FOUND);
				return;
			}

			if (HttpMethod.OPTIONS.matches(request.getMethod())) {
				response.setHeader("Allow", getAllowHeader());
				return;
			}

			// Supported methods and required session
			checkRequest(request);

			// Header phase
			if (new ServletWebRequest(request, response).checkNotModified(resource.lastModified())) {
				logger.trace("Resource not modified - returning 304");
				return;
			}

			// Apply cache settings, if any
			prepareResponse(response);

			// Check the media type for the resource
			MediaType mediaType = getMediaType(request, resource);
			if (mediaType != null) {
				if (logger.isTraceEnabled()) {
					logger.trace("Determined media type '" + mediaType + "' for " + resource);
				}
			}
			else {
				if (logger.isTraceEnabled()) {
					logger.trace("No media type found for " + resource + " - not sending a content-type header");
				}
			}

			// Content phase
			if (METHOD_HEAD.equals(request.getMethod())) {
				setHeaders(response, resource, mediaType);
				logger.trace("HEAD request - skipping content");
				return;
			}	
			
			// convert input stream to string
			try {
				InputStream in = resource.getInputStream();
				String InputString = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
		
				// replace with desire value
				StringBuffer sb = new StringBuffer();
				Pattern p = Pattern.compile(REGEX);
				Matcher m = p.matcher(InputString);
				while (m.find()) {
					String word = m.group(2);
					String replace = messageSource.getMessage(word, null, word, LocaleContextHolder.getLocale());
					m.appendReplacement(sb, replace);
				}
				m.appendTail(sb);
		
				// copy to output stream
				try {
					byte[] out = sb.toString().getBytes(StandardCharsets.UTF_8);
					// UTF-8 (uppercase of utf-8) doesn't work in google chrome properly
					response.setCharacterEncoding("utf-8");
					response.setContentLength(out.length);
					StreamUtils.copy(out, response.getOutputStream());
				} catch (NullPointerException ex) {
					// ignore, see SPR-13620
				} finally {
					try {
						in.close();
					} catch (Throwable ex) {
						// ignore, see SPR-12999
					}
				}				
			} catch (FileNotFoundException ex) {
				// ignore, see SPR-12999
			}				
			
		} else {
			super.handleRequest(request, response);
		}
		
	}

}
