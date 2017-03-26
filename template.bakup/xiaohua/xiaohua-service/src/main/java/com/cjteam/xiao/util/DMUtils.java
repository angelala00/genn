package com.cjteam.xiao.util;

import java.util.Calendar;
import java.util.Date;

public class DMUtils {

	public static int compareDate(Date date1) {
		Calendar c = Calendar.getInstance();
		c.clear();
		Date date2 = c.getTime();
		long quot;
		quot = date1.getTime() - date2.getTime();
		quot = quot / 1000 / 60 / 60 / 24;
		int day = new Long(quot).intValue();
		return day;
	}

	/**
	 * 计算两个日期之间相差的秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int secondsBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000);

		return Integer.parseInt(String.valueOf(between_days));
	}
}
