package com.zyk.jhtest.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2013-3-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" ,"ss mm HH dd MM ?"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

    /**
     * 得到日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String date,String pattern) {
        return DateFormatUtils.format(parseDate(date), pattern);
    }

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}
    /**
     * 得到date天数的字符串 格式（dd）
     */
    public static String getDay(Date date) {
        return formatDate(date, "dd");
    }

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 过去的分钟
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long pastMinutes(Date date1,Date date2){
		long t = date1.getTime()-date2.getTime();
		return t/(60*1000);
	}

	/**
	 * 得到几天前的日期
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d,int day){
		 Calendar now =Calendar.getInstance();
		 now.setTime(d);
		 now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
		 return now.getTime();
	}
	/**
	 *
	  * getDateAfter(获取之后几天)
	  *
	  * @Title: getDateAfter
	  * @author :taoq
	  * @Description: TODO
	  * @param @param today
	  * @param @param day
	  * @param @return    设定文件
	  * @return Date    返回类型
	  * @throws
	 */
	public static Date getDateAfter(Date today,int day){
		Calendar now =Calendar.getInstance();
		now.setTime(today);
		now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
		return now.getTime();
	}
	/**
	 *
	  * getDateAfter(获取之后几小时)
	  *
	  * @Title: getDateAfterHour
	  * @author :wangj
	  * @Description: TODO
	  * @param @param today
	  * @param @param day
	  * @param @return    设定文件
	  * @return Date    返回类型
	  * @throws
	 */
	public static Date getDateAfterHour(Date today,int hour){
		Calendar now =Calendar.getInstance();
		now.setTime(today);
		now.set(Calendar.HOUR,now.get(Calendar.HOUR)+hour);
		return now.getTime();
	}
	/**
	 * 得到每一天日期
	 * @param beginDay
	 * @param EndDay
	 * @param type 1:yyyy-MM-dd  2：MM月dd日
	 * @return
	 */
	public static String getEveryDate(Date beginDay,Date EndDay,int type){
		String strDate = null;
		SimpleDateFormat sdf = null;
		if(type == 1){
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}else{
			sdf = new SimpleDateFormat("MM月dd日");
		}
		long t = (EndDay.getTime()-beginDay.getTime())/(24*60*60*1000);			//相差天数
		for(int i = (int)t ; i>=0 ; i--){
			if(strDate == null){
				strDate = sdf.format(getDateBefore(EndDay,i));
			}else{
				strDate += ","+sdf.format(getDateBefore(EndDay,i));
			}
		}
		return strDate;
	}

	/**
	 * 两个日期相减，获取两者之间的时间间隔（秒）
	 *
	 * @param date1 被减时间
	 * @param date2 减时间
	 * @return 秒数
	 */
    public static int subSecond( Date date1, Date date2 ){
        //实例化Calendar
        Calendar calendar = Calendar.getInstance();
        //获取第一个日期对应的毫秒数
        calendar.setTime(date1);
        long timethis = calendar.getTimeInMillis();
        //获取第二个日期对应的毫秒数
        calendar.setTime(date2);
        long timeend = calendar.getTimeInMillis();
        //计算两个日期的间隔时间
        long theday = (timethis - timeend) / 1000;
        return (int)theday;
    }

    /**
     * Date字符串转ZonedDateTime
     *
     * @param dataTime 字符串时间
     * @return ZonedDateTime
     */
    public static ZonedDateTime dateTimeStrToZoned(String dateTime) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parsePatterns[1]);
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
		return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
	}

    /**
     * Date字符串转ZonedDateTime
     *
     * @param dataTime 字符串时间
     * @return ZonedDateTime
     */
    public static ZonedDateTime dateTimeStrToZoned(String dateTime,String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(dateTime, formatter);
        return localDate.atStartOfDay(ZoneId.systemDefault());
    }
    /**
     * Date字符串转ZonedDateTime（time 和 amOrPm 不能同时为null
     * time : 自定义时间部分； amOrPm : 0  上午-00:00:00; 1  下午-23:59:59）
     *
     * @param date 日期部分
     * @param time 时间部分
     * @param amOrPm 0 00:00:00/ 1 23:59:59
     * @return ZonedDateTime
     */
    public static ZonedDateTime dateTimeStrToZoned(String date, String time, Integer amOrPm) {
        String blankChar = " ";
        String dateTime = new String();
        if((org.springframework.util.StringUtils.isEmpty(time) && amOrPm == null) || (!org.springframework.util.StringUtils.isEmpty(time) && amOrPm != null)){
            return null;
        }else if(!StringUtils.isEmpty(time)){
            dateTime = date + blankChar + time;
        }else if(amOrPm != null && amOrPm.equals(0)){
            dateTime = date + blankChar + "00:00:00";
        }else if(amOrPm != null && amOrPm.equals(1)){
            dateTime = date + blankChar + "23:59:59";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parsePatterns[1]);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    }





    /**
     * 获取当前时间的ZonedDateTime类型
     *
     * @param dataTime 字符串时间
     * @return ZonedDateTime
     */
    public static ZonedDateTime getZonedDateTime() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parsePatterns[1]);
		LocalDateTime localDateTime = LocalDateTime.parse(getDate(parsePatterns[1]), formatter);
		return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
	}

    /**
	 *
	  * getSecondBefore(获取之前几秒的时间)

	  * @Title: getSecondBefore
	  * @author :taoq
	  * @Description: TODO
	  * @param @param today
	  * @param @param seconds
	  * @param @return    设定文件
	  * @return Date    返回类型
	  * @throws
	 */
	public static Date getSecondBefore(Date today,int seconds){
		Calendar now =Calendar.getInstance();
		now.setTime(today);
		now.set(Calendar.SECOND,now.get(Calendar.SECOND)-seconds);
		return now.getTime();
	}
	/**
	 *
	  * getSecondBefore(获取之后几秒的时间)

	  * @Title: getSecondBefore
	  * @author :taoq
	  * @Description: TODO
	  * @param @param today
	  * @param @param seconds
	  * @param @return    设定文件
	  * @return Date    返回类型
	  * @throws
	 */
	public static Date getSecondAfter(Date today,int seconds){
		Calendar now =Calendar.getInstance();
		now.setTime(today);
		now.set(Calendar.SECOND,now.get(Calendar.SECOND)+seconds);
		return now.getTime();
	}
    /**
     * Date字符串转ZonedDateTime（time 和 amOrPm 不能同时为null
     * time : 自定义时间部分； amOrPm : 0  上午-00:00:00; 1  下午-23:59:59）
     *
     * @param date 日期部分
     * @param time 时间部分
     * @param amOrPm 0 00:00:00/ 1 23:59:59
     * @return ZonedDateTime
     */
    public static ZonedDateTime dateTimeStrToZoned123(String date, String time, Integer amOrPm) {
        String blankChar = " ";
        String dateTime = new String();
        if((org.springframework.util.StringUtils.isEmpty(time) && amOrPm == null) ||
            (!org.springframework.util.StringUtils.isEmpty(time) && amOrPm != null)){
            return null;
        }else if(!org.springframework.util.StringUtils.isEmpty(time)){
            dateTime = date + blankChar + time;
        }else if(amOrPm != null && amOrPm.equals(0)){
            dateTime = date + blankChar + "00:00:00";
        }else if(amOrPm != null && amOrPm.equals(1)){
            dateTime = date + blankChar + "23:59:59";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parsePatterns[1]);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    }

    /**
     * 转换zonedDateTime时间格式，返回zonedDateTime格式的字符串时间，Example: 2015-08-18T06:36:40.321Z
     * @param zonedDateTime
     * @return
     */
    public static String parseZonedDate(ZonedDateTime zonedDateTime) {
        Instant instant = zonedDateTime.toInstant();
        Instant instantTruncatedToMilliseconds = Instant.ofEpochMilli(instant.toEpochMilli());
        return instantTruncatedToMilliseconds.toString();
    }

    /**
     * 获取date的cron类型
     *
     * @param Date 字符串时间
     * @return cron类型的日期
     */
    public static String getCron(Date date){
    	String formatDate="";
    	formatDate=formatDate(date,parsePatterns[6]);
    	return formatDate;
    }

    /**
     * 获取当前时间的之后某个时间的cron类型
     *
     * @return cron类型的日期
     */
    public static String getCron(Integer minute){
    	String formatTimeStr = "";
    	Calendar nowTime = Calendar.getInstance();
    	nowTime.add(Calendar.MINUTE, minute);
    	formatTimeStr = formatDate(nowTime.getTime(),parsePatterns[6]);
    	return formatTimeStr;
    }

    /**
     * 将mongo中获取出来的时间转换成String
     * 这里在mongo中存的时间格式是Date格式
     * @author zmj
     * @return
     */
    public static String parseMongoDateToStr(Object obj,String pattern){
        return formatDate(new Date(obj+""),pattern);
    }


    /**
     *
      * getMonday(获取传入日期的周一)
      *
      * @Title: getMonday
      * @author :wangj
      * @Description: 获取传入日期的周一,可根据传入的day，获取后几天的数据，传0则为星期一
      * @param  @param startTime
      * @param  @return   设定文件
      * @return String    返回类型
      * @throws
     */
    public static String getMonday(Date date1, Integer day) {

//		String monday = startTime;
//
//		Date date1 = parseDate(startTime);

		Calendar cal = Calendar.getInstance();
		//设置日期，否则会查询当天所在的周一
		cal.setTime(date1);
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DAY_OF_WEEK, day);
		String monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

		return monday;
    }

    /**
     *
      * nextMonthFirstDate(获取传入时间之后某个月的第一天或者最后一天)
      *
      * @Title: nextMonthFirstDate
      * @author :wangj
      * @Description: 获取传入时间之后某个月的第一天或者最后一天
      * @param  @param startTime
      * @param  @param count
      * @param  @param type  0：获取第一天日期；1：获取最后一条的日期
      * @param  @return   设定文件
      * @return Date    返回类型
      * @throws
     */
    public static Date nextMonthFirstDate(Date date1, Integer count, Integer type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        if (type.intValue() == 0) {
        	calendar.set(Calendar.DAY_OF_MONTH, 1);
        } else {
        	int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        	calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        }
        calendar.add(Calendar.MONTH, count);
        return calendar.getTime();
    }


    /**
     *
      * timetimeBetween(获取对应时间类型的间隔数)
      *
      * @Title: timetimeBetween
      * @author :wangj
      * @Description: 获取两个日期之间的天数、周数、月数，相邻的两个周间隔为1，每周是按照周日到周六计算
      * @param  @param startTime
      * @param  @param endTime
      * @param  @param datetype  0:天数； 1：周数； 2：月数
      * @param  @return   设定文件
      * @return int    返回类型
      * @throws
     */
    public static int timeBetween(Date date1, Date date2, Integer datetype) {
		// 天数
		int days = 0;
		int weeks = 0;
		int months = 0;
		//如果开始日期在结束日期之前，调整日期位置
		if (date1.after(date2)) {
            Date t = date1;
            date1 = date2;
            date2 = t;
        }

		Calendar can1 = Calendar.getInstance();
		can1.setTime(date1);
		Calendar can2 = Calendar.getInstance();
		can2.setTime(date2);
		int year1 = can1.get(Calendar.YEAR);
		int year2 = can2.get(Calendar.YEAR);

		days -= can1.get(Calendar.DAY_OF_YEAR);
		days += can2.get(Calendar.DAY_OF_YEAR);
		weeks -= can1.get(Calendar.WEEK_OF_YEAR);
		weeks += can2.get(Calendar.WEEK_OF_YEAR);
		months -= can1.get(Calendar.MONTH);
		months += can2.get(Calendar.MONTH);

		Calendar can = can1;

		for (int i = 0; i < Math.abs(year2 - year1); i++) {
			days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
			weeks += can.getActualMaximum(Calendar.WEEK_OF_YEAR);
			months += can.getActualMaximum(Calendar.MONTH) + 1;
			can.add(Calendar.YEAR, 1);
		}

		if (datetype.intValue() == 0) {
			return days;
		} else if (datetype.intValue() == 1) {
			return weeks;
		} else if (datetype.intValue() == 2) {
			return months;
		}
		return 0;
    }

    /**
     * getYearMonth(获取今年的某月)
     *
     * @Title: getYearMonth
     * @author :Xufg
     * @Description: 获取今年的某月
     * @param  @param month
     * @param  @return   设定文件
     * @return Date    返回类型
     * @throws
     */
    public static Date getYearMonth(int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.MONTH, month - 1);// 1月从0开始
        return c.getTime();
    }

    /**
     * getLastYearMonth(获取去年的某月)
     *
     * @Title: getLastYearMonth
     * @author :Xufg
     * @Description: 获取去年的某月
     * @param  @param month
     * @param  @return   设定文件
     * @return String    返回类型
     * @throws
     */
    public static Date getLastYearMonth(int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        c.set(Calendar.MONTH, month - 1);// 1月从0开始
        /*Date date = c.getTime();
        return formatDate(date, "yyyy-MM");*/
        return c.getTime();
    }

    /**
     * getMonthFirstDay(获取某个月的第一天)
     *
     * @Title: getMonthFirstDay
     * @author :Xufg
     * @Description: 获取某个月的第一天
     * @param  @param date
     * @param  @return   设定文件
     * @return Date    返回类型
     * @throws
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
    	/*c.set(Calendar.HOUR, 1);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);*/
        return c.getTime();
    }

    /**
     * getMonthLastDay(获取某个月的最后一天)
     *
     * @Title: getMonthLastDay
     * @author :Xufg
     * @Description: 获取某个月的最后一天
     * @param  @param date
     * @param  @return   设定文件
     * @return Date    返回类型
     * @throws
     */
    public static Date getMonthLastDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, lastDay);
        return c.getTime();
    }

    /**
     * 将毫秒时间数转换成时间格式字符串
     * @param time
     * @return
     */
    public static String millsToDate(String time ){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
        Date date2 = new Date();
        date2.setTime(Long.valueOf(time));
        return  simpleDateFormat.format(date2);
    }



}
