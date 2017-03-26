package com.cjteam.mrile.api.controller.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String format(Date date, String string) {
		SimpleDateFormat fmtDate = new SimpleDateFormat(string);
		return fmtDate.format(date);
	}

}
