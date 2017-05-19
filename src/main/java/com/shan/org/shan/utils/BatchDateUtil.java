package com.shan.org.shan.utils;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * 
 */
public class BatchDateUtil {

	private static SimpleDateFormat F_D = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat F_S = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat F_M = new SimpleDateFormat("yyyy-MM");
	
	public static Timestamp getStartTime(){  
		Date date = new Date();
		  GregorianCalendar gc = new GregorianCalendar();
		  gc.setTime(date);
		  Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
		    * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
		    * 1000);
		  return new Timestamp(date2.getTime()); 
    } 
	
	 public static Timestamp getDayBegin() {
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.HOUR_OF_DAY, 0);
		  cal.set(Calendar.SECOND, 0);
		  cal.set(Calendar.MINUTE, 0);
		  cal.set(Calendar.MILLISECOND, 001);
		  return new Timestamp(cal.getTimeInMillis());
		 }

	public static Long getBatch(Date date) {
		if (date == null) {
			return 0L;
		}
		Date n = null;
		try {
			String format = F_D.format(date);
			n = F_D.parse(format);
			Long batch = n.getTime() / 1000;
			return batch;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime() / 1000;
	}

	/**
	 * 获取mysql的10位时间的日期
	 * 
	 * @param time
	 * @return
	 */
	public static Date getDate(Long time) {
		Date d = new Date(time * 1000);
		return d;
	}

	/**
	 * 根据当前时间获取前十二个月的一号零点的时间戳
	 * @param date
	 * @return
	 */
	public static Long[] getMonths(Date date) {
		Long[] months = new Long[13];
		if (date == null) {
			return null;
		}
		Date n = null;
		try {
			Calendar cal = Calendar.getInstance();
			for (int i = 0; i < months.length; i++) {
				cal.setTime(date);
				cal.add(Calendar.MONTH, -i);
				Date dateBefore = cal.getTime();
				String format = F_M.format(dateBefore);
				n = F_M.parse(format);
				Long batch = n.getTime() / 1000;
				months[i] = batch;
			}
			return months;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return months;
	}
	
	/**
	 * 根据当前时间获取前十五天零点的时间戳
	 * @param date
	 * @return
	 */
	public static Long[] getDays(Date date) {
		Long[] days = new Long[16];
		if (date == null) {
			return null;
		}
		Date n = null;
		try {
			for (int i = 0; i < days.length; i++) {
				String format = F_S.format(date.getTime()-i*24*60*60*1000);
				n = F_S.parse(format );
				Long batch = n.getTime() / 1000;
				days[i] = batch;
			}
			return days;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}
	

	public static Long getFormatDay(Date date){
		String format = F_S.format(date);
		return Long.valueOf(format.replace("-", ""));
	}
	
	public static Long getFormatMonth(Date date){
		String format = F_M.format(date);
		return Long.valueOf(format.replace("-", ""));
	}
	
	public static String[] getMonthForJSP(Date date){
		String[] s = new String[12];
		Long[] months = getMonths(new Date());
		int index = 0;
		for (int i = months.length - 1; i > 0 ; i--) {
			Date date2 = getDate(months[i]);
			String format = F_M.format(date2);
			String[] split = format.split("-");
			if("01".equals(split[1])){
				s[index++] = "一月";
			}
			if("02".equals(split[1])){
				s[index++] = "二月";
			}
			if("03".equals(split[1])){
				s[index++] = "三月";
			}
			if("04".equals(split[1])){
				s[index++] = "四月";
			}
			if("05".equals(split[1])){
				s[index++] = "五月";
			}
			if("06".equals(split[1])){
				s[index++] = "六月";
			}
			if("07".equals(split[1])){
				s[index++] = "七月";
			}
			if("08".equals(split[1])){
				s[index++] = "八月";
			}
			if("09".equals(split[1])){
				s[index++] = "九月";
			}
			if("10".equals(split[1])){
				s[index++] = "十月";
			}
			if("11".equals(split[1])){
				s[index++] = "十一月";
			}
			if("12".equals(split[1])){
				s[index++] = "十二月";
			}
		}
		return s;
	}
	
	/**
	 * 将时间戳转为时间格式
	 * @return
	 */
	public static String getDATE(Long time){
		Long timestamp = time*1000;

		String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp)); 
		return date;
	}
	
	public static Long get30Days(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
         
        //过去一月
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = sdf.format(m);
        Long day = null;
		try {
			day = sdf.parse(mon).getTime()/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return day;
	}
	
	public static Long getzuotian(Date date){
	   Calendar calendar = new GregorianCalendar();
	   calendar.setTime(date);
	   calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
	   date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(date);
	   Long day=null;
	   try {
			day = formatter.parse(dateString).getTime()/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return day;
	}
	
	public static Long getqiantian(Date date){
		   Calendar calendar = new GregorianCalendar();
		   calendar.setTime(date);
		   calendar.add(calendar.DATE,-2);//把日期往后增加一天.整数往后推,负数往前移动
		   date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String dateString = formatter.format(date);
		   Long day=null;
		   try {
				day = formatter.parse(dateString).getTime()/1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   return day;
		}
	
	
	@Test
	public void testName() throws Exception {
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
         
        //过去一月
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = sdf.format(m);
	}
}
