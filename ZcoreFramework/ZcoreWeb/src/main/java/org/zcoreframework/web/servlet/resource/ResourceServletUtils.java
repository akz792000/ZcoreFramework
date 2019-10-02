/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

public abstract class ResourceServletUtils {
		
	public static URL getJarResource(String resourcePath) {
		if (resourcePath.startsWith("/")) 
			resourcePath = resourcePath.substring(1);
		return ClassUtils.getDefaultClassLoader().getResource(resourcePath);
	}
	
	public static File getFile(String resourcePath) throws FileNotFoundException {
		URL fileUrl = getJarResource(resourcePath);
		return ResourceUtils.getFile(fileUrl);
	}
	
	public static URL jndiFileURL(URL jarUrl) throws MalformedURLException {
		String urlFile = jarUrl.getFile();
		int separatorIndex = urlFile.indexOf("localhost");
		if (separatorIndex != -1) {
			String jarFile = urlFile.substring(0, separatorIndex);
			try {
				return new URL(jarFile);
			}
			catch (MalformedURLException ex) {
				// Probably no protocol in original jar URL, like "jar:C:/mypath/myjar.jar".
				// This usually indicates that the jar file resides in the file system.
				if (!jarFile.startsWith("/")) {
					jarFile = "/" + jarFile;
				}
				return new URL(ResourceUtils.FILE_URL_PREFIX + jarFile);
			}
		}
		else {
			return jarUrl;
		}
	}
	
	public static long getLastModified(HttpServletRequest request, String urlStr) {
		try {
			String localResourcePath = urlStr.replace(request.getContextPath() + "/resources", "/web-resources");
			URL resourceURL = request.getServletContext().getResource(localResourcePath);
			
			File file;
			if (resourceURL == null) {
				resourceURL = getJarResource(urlStr.replace(request.getContextPath() + "/resources", "META-INF/web-resources"));
				if (resourceURL == null)
					return 0;
				if (ResourceUtils.isJarURL(resourceURL)) 
					resourceURL = ResourceUtils.extractJarFileURL(resourceURL);	
				file = ResourceUtils.getFile(resourceURL);
			} else {
				if (resourceURL.getProtocol().equals("jndi")) {
					// this is for compatibility with TOMCAT
					String filePath = request.getServletContext().getRealPath(localResourcePath);
					file = ResourceUtils.getFile(filePath);
				} else						
					file = ResourceUtils.getFile(resourceURL);
			}	
			
			return file.lastModified();
			
		} catch (FileNotFoundException e) {
			
		} catch (MalformedURLException e) {
			
		}
		return 0;
	}

}
