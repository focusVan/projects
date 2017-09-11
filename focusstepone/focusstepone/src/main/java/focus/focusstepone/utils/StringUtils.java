package focus.focusstepone.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {
	/*
	 * 计算字符串的MD5(16位)
	 */
	public static String getStrMD5(String str) {
		if (str != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] bytes = md.digest(str.getBytes());
				return byte2HexString(bytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	/**
	 * 将指定byte数组转换成16位字符串
	 */
	private static String byte2HexString(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();
		for (int i=0; i<bytes.length; i++) {
			String hex = Integer.toString(bytes[i] & 0xFF, 16);
			if (hexString.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString().substring(8,24);
	}
}
