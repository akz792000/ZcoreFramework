/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import java.security.MessageDigest;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.codec.Hex;

public class Md5Utils {
	
	public static String getEncrypt(String rawPass) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		return md5.encodePassword(rawPass, null);
	}
	
	public static String getEncrypt(byte[] msg) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] digest = messageDigest.digest(msg);
			digest = messageDigest.digest(digest);
			return new String(Hex.encode(digest));
		} catch (Exception e) {
			return null;
		}
	}	
	
}
