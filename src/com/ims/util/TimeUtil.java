package com.ims.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	public static SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:MM:ss");
	public static SimpleDateFormat DateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");

	
	public static String getMoth(Date date){
		if(date == null) date = new Date();
		return monthFormat.format(date);
	}
	
	
	public static int subHours(String dateStr,int hours){
		Calendar c2 = Calendar.getInstance();
		try {
			c2.setTime(DateTimeFormat.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		c2.add(Calendar.HOUR, hours);
		return new Date().compareTo(c2.getTime());
	}
	
	
}
