package com.jc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Utils {

	public static List<Map<String, Object>> changeTbInfos(
			List<Map<String, Object>> tbinfos) {
		return tbinfos;
	}

	public static String toFirstUpperCase(String subname) {
		return subname.substring(0, 1).toUpperCase()
				+ subname.substring(1, subname.length());
	}

	public static String toFirstLowerCase(String subname) {
		return subname.substring(0, 1).toLowerCase()
				+ subname.substring(1, subname.length());
	}

	public static String getJavaType(String trim) {
		String type = "Object";
		if (trim.startsWith("int") || trim.contains("int")
				|| trim.contains("bit")) {
			type = "Integer";
		} else if (trim.startsWith("varchar")) {
			type = "String";
		} else if (trim.contains("datetime") || trim.contains("timestamp")) {
			type = "Date";
		} else if (trim.contains("timestamp")) {
			type = "Date";
		}
		return type;
	}

	public static void prtln(String str) {
		boolean prt = true;
		if (prt) {
			System.out.println("jclog:"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date()) + str);
		}
	}

	public static String getEntityName(String tbname) {
		String[] subnames = tbname.split("_");
		String tempname = "";
		for (String subname : subnames) {
			tempname += Utils.toFirstUpperCase(subname);
		}
		return tempname;
	}
	public static String getEntityName(String tbname, boolean isFirstLowerCase) {
		if (isFirstLowerCase) {
			return Utils.toFirstLowerCase(getEntityName(tbname));
		} else {
			return getEntityName(tbname);
		}
	}

	public static String formatNumber(int i) {
		if (i >= 0 && i < 10) {
			return "0" + i;
		} else {
			return "" + i;
		}
	}

	public static String getCategoryName(String tbname) {
		String[] subnames = tbname.split("_");
		String cname = subnames.length > 1 ? subnames[0] : "normal";
		if (cname.equals("public") || cname.equals("Public")) {
			cname = "publicc";
		}
		return Utils.toFirstLowerCase(cname);
	}

	public static String getJspName(String tbname) {
		String entity = Utils.getEntityName(tbname);
		entity = entity.substring(0, 1).toLowerCase()
				+ entity.substring(1, entity.length());
		return entity;
	}

	private static String getJspPath(String tbname, String separator) {
		return "/jsp/" + getCategoryName(tbname) + separator
				+ tbname + separator;
	}

	public static String getActionPackage(String tbname, String actionPackage) {
		return actionPackage + getCategoryName(tbname);
	}

	public static String parseField(String sourceStr) {
		return Utils.toFirstLowerCase(parseEntityName(sourceStr));
	}

	public static String parseEntityName(String sourceStr) {
		String[] parts = StringUtils.lowerCase(sourceStr).split("_");
		StringBuilder sb = new StringBuilder();
		for (String part : parts) {
			part = StringUtils.trim(part);
			if (StringUtils.isNotBlank(part)) {
				sb.append(StringUtils.lowerCase(part));
			}
		}
		return sb.toString();
	}

	private static String getTbName2(String tbname) {
		return null;
	}

	public static String getActionName(String tbname) {
		return Utils.getEntityName(tbname) + "Action";
	}

}
