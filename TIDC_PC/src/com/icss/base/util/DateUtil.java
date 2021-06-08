package com.icss.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/*
 * 时间处理类
 * 根据当前时间获取昨天、上一周、上一个月的日期
 */
public class DateUtil {
	
	private static Calendar cal = Calendar.getInstance();
	private static SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getDateByUpdateType(String date, String updateTpye){
		
		if("C".equals(updateTpye)){
			return date;
		}
		
		if("D".equals(updateTpye)){
			return getLastDate(date);
		}
		
		if("W".equals(updateTpye)){
			return getLastWeekDate(date);
		}
		
		if("M".equals(updateTpye)){
			return getLastMonthDate(date);
		}
		
		return date;
	}
	
	/*
	 * D 昨天
	 * W 上周
	 * M 上个月
	 */
	public static String getDateByUpdateType(String updateTpye){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//当前系统日期
		cal = Calendar.getInstance();
		String currDate=sdf.format(cal.getTime());
		
		return getDateByUpdateType(currDate, updateTpye);

	}
	public static String getNowDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//当前系统日期
		cal = Calendar.getInstance();
		String currDate=sdf.format(cal.getTime());
		return currDate;
	}
	
	private static Calendar transStrToCal(String str){
		Date d = new Date();
		try {
			d = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(d);
		
		return cal;
	}
	
	public static String getLastDate(String date){
		cal=transStrToCal(date);
		cal.add(Calendar.DATE,   -1);
		String lastDate=sdf.format(cal.getTime());
		return lastDate;
	}
	
	private static String getLastWeekDate(String date){
		cal=transStrToCal(date);
		cal.add(Calendar.DATE,   -7);
		String lastWeekDate=sdf.format(cal.getTime());
		return lastWeekDate;
	}
	
	private static String getLastMonthDate(String date){
		cal=transStrToCal(date);
		cal.add(Calendar.MONTH,   -1);
		String lastMonthDate=sdf.format(cal.getTime());
		return lastMonthDate;
	}
	
	public static String getDateTimeStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		//当前系统日期
		cal = Calendar.getInstance();
		
		return sdf.format(cal.getTime());
	}
	
	public static void main(String[] args) {
		System.out.println("前一天："+getLastDate("2015-12-01"));
		
		System.out.println(getDateTimeStr());
		
		String lastDate=DateUtil.getDateByUpdateType("D");
		System.out.println("lastDate:"+lastDate);
		
		String lastWeekDate=DateUtil.getDateByUpdateType("W");
		System.out.println("lastWeekDate:"+lastWeekDate);
		
		String lastMonthDate=DateUtil.getDateByUpdateType("M");
		System.out.println("lastMonthDate:"+lastMonthDate);
		
		String lastWeekDate2=DateUtil.getDateByUpdateType("2015-04-12","W");
		System.out.println("lastWeekDate2:"+lastWeekDate2);
		
		lastDate=DateUtil.getDateByUpdateType("D");
		System.out.println("lastDate:"+lastDate);
		
		lastWeekDate=DateUtil.getDateByUpdateType("W");
		System.out.println("lastWeekDate:"+lastWeekDate);
		System.out.println(getCurrentTime());
		
		
		System.out.println(getNowDate());
		System.out.println(getYear());
		System.out.println(getMonth());
	}
	//获取当前日期，日期格式：YYYY-MM-DD
	public static String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式 
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); 
		String date = df.format(new Date());// new Date()为获取当前系统时间
		return date;
	}
	//获取当前时间，时间格式：HH:MM:SS,
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("kk:mm:ss");//设置日期格式
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); 
		String time = df.format(new Date());// new Date()为获取当前系统时间
		return time;
	}
	//自定义时间与当前时间相加减，日期格式：YYYY-MM-DD
	public static String getChangeDate(int num) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		//对时间进行加减
	    Calendar calendar = new GregorianCalendar(); 
	    calendar.setTime(new Date()); 
	    calendar.add(Calendar.DATE, num);//把日期往后增加一天.整数往后推,负数往前移动 
	    Date todate=calendar.getTime();
	    //转换时间的格式
		String date = df.format(todate);// new Date()为获取当前系统时间
		return date;
	}
	// 获取当前年
	public static int getYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");// 设置日期格式
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		String date = df.format(new Date());// new Date()为获取当前系统时间
		return Integer.valueOf(date);
	}
	// 获取当前年月
	public static int getYearMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");// 设置日期格式
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		String date = df.format(new Date());// new Date()为获取当前系统时间
		return Integer.valueOf(date);
	}	
	// 获取当前月
	public static int getMonth() {
		SimpleDateFormat df = new SimpleDateFormat("MM");// 设置日期格式
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		String date = df.format(new Date());// new Date()为获取当前系统时间
		return Integer.valueOf(date);
	}
}
