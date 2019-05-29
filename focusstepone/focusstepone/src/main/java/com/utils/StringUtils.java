package com.utils;

import org.apache.commons.codec.digest.DigestUtils;

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
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static void main(String[] args) {
		/*String ua = "HUAWEI MLA-AL10,M5S,REDMI NOTE 4X,HUAWEI TIT-CL10,MI MAX,SAMSUNG-SM-G925A,SM-G925P,VIVO X5MAX+,SM-G9250,SM-G925V,COOLPAD 8690,SM-G925F,MI MAX（32G),VIVO S7I(T),REDMI NOTE3双网通,NOKIA 3.1 PLUS,SM-G925S,SM-G925L,SM-G925I,SM-G928S,SM-G925K,SM-G925T";
		String[] array = ua.split(",");
		for (String temp : array) {
			System.out.println(getStrMD5(temp));
		}*/

		String tempUa = DigestUtils.md5Hex("VIVO X5MAX+");
		System.out.println(tempUa);


	}
}
