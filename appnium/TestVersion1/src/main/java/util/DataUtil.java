package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataUtil {

	public static String formate1 = "YYYY-MM-dd";
	public static String formate2 = "YYYY";
	public static String formate3 = "YYYY-MM";
	public static String formate4 = "YYYYMMdd";
	public static String formate5="yyyy-MM-dd HH:mm:ss";
	/**
	 * 时间格式转换
	 * 
	 * @param date
	 * @param form
	 * @return
	 */
	public static String format(Date date, String form) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat(form);
		return formater.format(date);
	}

	/**
	 * 判断是否相同
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static boolean iSEqual(Date flag, Date date) {
		return date.getTime() == flag.getTime();
	}

	/**
	 * 字符串转Date
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date textToDate(String dateStr, String pattern) {
		try {
			Date date = new SimpleDateFormat(pattern).parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 得到月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 得到天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int date2 = calendar.get(Calendar.DATE);
		return date2;
	}

	/**
	 * 增加年数
	 * 
	 * @param date
	 * @param yearNum
	 * @return
	 */
	public static Date addYears(Date date, int yearNum) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, yearNum);
		return calendar.getTime();
	}

	/**
	 * 增加月份
	 * 
	 * @param date
	 * @param monthNum
	 * @return
	 */
	public static Date addMonths(Date date, int monthNum) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthNum);
		return calendar.getTime();
	}

	/**
	 * 增加天数
	 * 
	 * @param date
	 * @param dateNum
	 * @return
	 */
	public static Date addDates(Date date, int dateNum) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dateNum);
		return calendar.getTime();
	}

	/**
	 * 得到时间戳
	 * 
	 * @return
	 */
	public static long getTime() {
		return Calendar.getInstance().getTimeInMillis();

	}

	/**
	 * 得到知道日期的时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long getTime(Date date) {
		return date.getTime();

	}

	/**
	 * 将年月日转为日期
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static Date createDate(int year, int month, int date) {
		Calendar calendar = new GregorianCalendar(year, month - 1, date);
		return calendar.getTime();
	}

	/**
	 * 将指定格式的日期/时间字符串转换成Date格式
	 *
	 * @param strDate   String类型，日期字符
	 * @param strFormat String类型，格式
	 * @return Date类型
	 * @throws Exception
	 */
	public static Date toUtilDate(String strDate, String strFormat) {
		try {
			if (strDate == null || strDate.equals("")) {
				return null;
			} else {
				SimpleDateFormat _formatdate = new SimpleDateFormat(strFormat, Locale.getDefault());
				Date _date = new Date((_formatdate.parse(strDate)).getTime());
				return _date;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 将指定格式的日期/时间字符串转换成Date格式
	 *
	 * @param strDate   String类型，日期字符
	 * @param strFormat String类型，格式
	 * @return Date类型
	 */
	public static java.sql.Date toSQLDate(String strDate, String strFormat) {
		try {
			if (strDate == null || strDate.equals("")) {
				return null;
			} else {
				SimpleDateFormat _formatdate = new SimpleDateFormat(strFormat, Locale.getDefault());
				java.sql.Date _date = new java.sql.Date((_formatdate.parse(strDate)).getTime());
				return _date;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到系统当前时间
	 * 
	 * @param iType
	 * @return
	 */
	public static String getCurrentSysTime(int iType) {

		Date dtNow = new Date(System.currentTimeMillis());
		String dateString = "";

		try {
			SimpleDateFormat formatter = null;
			switch (iType) {
			case 1:
				formatter = new SimpleDateFormat("yyyy.MM.dd");
				break;
			case 2:
				formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
				break;
			case 3:
				formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
				break;
			case 4:
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				break;
			case 5:
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				break;
			case 6:
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
				break;
			case 7:
				formatter = new SimpleDateFormat("yyyy-MM");
				break;
			case 8:
				formatter = new SimpleDateFormat("yyyyMMdd");
				break;
			default:
				formatter = new SimpleDateFormat("yyyy.MM.dd");
				break;
			}
			dateString = formatter.format(dtNow);
		} catch (Exception e) {
			e.printStackTrace();
			dateString = "";
		}
		return dateString;
	}

	/**
	 * 获得两个日期相隔天数
	 *
	 * @param dtBeginDate
	 * @param dtEndDate
	 * @return
	 */
	public static long intervalDays(Date dtBeginDate, Date dtEndDate) {
		long interval = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String begindate = format.format(dtBeginDate);
			String enddate = format.format(dtEndDate);
			Date date_begindate = format.parse(begindate);
			Date date_enddate = format.parse(enddate);
			interval = date_enddate.getTime() - date_begindate.getTime();
			interval = interval / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return interval;
	}

	/**
	 * 传入一个时间，得到该时间所在月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * 传入一个时间，得到该时间所在月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * 传入一个时间和月份，得到相加后的时间
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date getDateAddMonth(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num);

		return calendar.getTime();
	}

	/**
	 * 只精确到日期，舍去时分秒
	 *
	 * @param time
	 * @return Date
	 */
	public static Date correctToDate(Date time) {
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(time);
		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.set(Calendar.HOUR_OF_DAY, 0);
		return gc1.getTime();
	}
	
	/**
	 * 日期比较函数，相等返回0,大于大于零的值,小于返回小于零的值
	 * @param dateA
	 * @param dateB
	 * @return
	 */
    public static int dateCompare(Date dateA, Date dateB) {
        SimpleDateFormat format = new SimpleDateFormat(formate5);
        String datea = format.format(dateA);
        String dateb = format.format(dateB);
        return datea.compareTo(dateb);
    }
    
   /**
    * 日期比较函数，相等返回0,大于大于零的值,小于返回小于零的值（只比较年月）
    * @param dateA
    * @param dateB
    * @return
    */
    public static int dateCompareOne(Date dateA, Date dateB) {
        SimpleDateFormat format = new SimpleDateFormat(formate3);
        String datea = format.format(dateA);
        String dateb = format.format(dateB);
        return datea.compareTo(dateb);
    }
    /**
     * java.util.Date转化成LocalDat
     * @param date
     * @return
     */
    public static LocalDate DateToLocaleDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId  = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * LocalDate转化成 java.util.Date
     * @param localDate
     * @return
     */
    public static Date LocalDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ChronoZonedDateTime<LocalDate> zonedDateTime = localDate.atStartOfDay(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }
}
