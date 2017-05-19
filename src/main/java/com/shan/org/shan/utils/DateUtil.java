package com.shan.org.shan.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil{

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	/**
	 * 获取开始时间
	 * @param num
	 * @return
	 */
	public static Long getBeginDateLong(int num){
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		c.add(c.DATE, num);
		long date = c.getTimeInMillis();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        logger.info("================"+sdf.format(new Date(calendar.getTimeInMillis())));
        return calendar.getTimeInMillis();
	}

	/**
	 * 获取结束时间
	 * @param num
	 * @return
	 */
	public static Long getEndDateLong(int num){
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		c.add(c.DATE, num);
		long date = c.getTimeInMillis();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        logger.info("================"+sdf.format(new Date(calendar.getTimeInMillis())));
        return calendar.getTimeInMillis();
	}

	public static Long getDateLong(String date) throws ParseException {
		Calendar dayc = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date daystart = df.parse(date);
		dayc.setTime(daystart);
        logger.info("================"+sdf.format(new Date(dayc.getTimeInMillis())));
		return dayc.getTimeInMillis();
	}

	public static String getDateTimeStr(Long date) {
		return sdf.format(new Date(date));
	}
}