package com.ims.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:MM:ss");
	public static boolean isNull(String source){
		return source == null || "".equals(source.trim());
	}
	
	public static String getDate(Date date){
		if(date == null) date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getTime(Date date){
		if(date == null) date = new Date();
		return TimeFormat.format(date);
	}
}
