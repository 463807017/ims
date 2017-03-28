package com.ims.util;

public class StringUtil {

	public static boolean isNull(String source){
		return source == null || "".equals(source.trim());
	}
}
