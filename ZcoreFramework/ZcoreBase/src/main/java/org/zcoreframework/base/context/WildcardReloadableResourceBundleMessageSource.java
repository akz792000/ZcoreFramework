/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.context;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

public class WildcardReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

	private PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	protected String determineRootDir(String location) {
		int prefixEnd = location.indexOf(":") + 1;
		int rootDirEnd = location.length();
		while ((rootDirEnd > prefixEnd) && (this.resourcePatternResolver.getPathMatcher().isPattern(location.substring(prefixEnd, rootDirEnd)))) {
			rootDirEnd = location.lastIndexOf('/', rootDirEnd - 2) + 1;
		}
		if (rootDirEnd == 0) {
			rootDirEnd = prefixEnd;
		}
		return location.substring(0, rootDirEnd);
	}

	public void setBasenames(String... basenames) {
		if (basenames != null) {
			List<String> baseNames = new ArrayList<>();
			for (int i = 0; i < basenames.length; i++) {
				String basename = basenames[i];
				Assert.hasText(basename, "Basename must not be empty");
				try {
					String locationPattern = basename.trim();
					Resource[] resources = this.resourcePatternResolver.getResources(locationPattern);
					String rootDirPath = determineRootDir(locationPattern);
					rootDirPath = StringUtils.substringAfter(rootDirPath, "classpath:");
					for (int j = 0; j < resources.length; j++) {
						basename = resources[j].getURL().getPath();
						if (basename.indexOf(".properties") == -1) {
							baseNames.add("classpath:" + rootDirPath + StringUtils.substringAfter(basename, rootDirPath));
						}
					}
				} catch (IOException exp) {
					this.logger.debug("No message source files found for basename " + basename + ".");
				}
			}
			super.setBasenames((String[]) baseNames.toArray(new String[baseNames.size()]));
		}
	}
}
