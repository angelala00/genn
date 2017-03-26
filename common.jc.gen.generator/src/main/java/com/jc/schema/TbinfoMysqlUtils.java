package com.jc.schema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jc.util.Utils;

public class TbinfoMysqlUtils implements ITbinfoUtils {

	@Override
	public List<Map<String, Object>> getTbInfos() throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<String> tbnames = TbinfoMysqlUtils.getTables();
		for (String tbname : tbnames) {
			Map<String, Object> map = new HashMap<String, Object>();
			list.add(map);
			Utils.prtln("   tbname:" + tbname);
			map.put("tbname", tbname);
			String tbnameJavaType = Utils.getEntityName(tbname);
			String tbnameJavaVar = Utils.getEntityName(tbname, true);
			map.put("tbnameJavaType", tbnameJavaType);
			map.put("tbnameJavaVar", tbnameJavaVar);
			
			// 读action模版
			String showColumns = "show full columns from " + tbname;
			Utils.prtln("showColumns:" + showColumns);
			con = TbinfoMysqlUtils.getcon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(showColumns);
			ResultSetMetaData rsmd = rs.getMetaData();
			int j = rsmd.getColumnCount();
			List<Map<String, String>> columns = new ArrayList<Map<String, String>>();
			map.put("columns", columns);
			while (rs.next()) {
				Utils.prtln("==");
				Map<String, String> column = new HashMap<String, String>();
				columns.add(column);
				column.put("name", StringUtils.trim(rs.getString(1)));
				column.put("nameJavaType", Utils.getEntityName(rs.getString(1)));
				column.put("nameJavaVar", Utils.getEntityName(rs.getString(1), true));
				column.put("name_java", StringUtils.trim(rs.getString(1)));
				column.put("name_Java", Utils.toFirstUpperCase(StringUtils.trim(rs.getString(1))));
				column.put("type", StringUtils.trim(rs.getString(2)));
				column.put("type_java", Utils.getJavaType(StringUtils.trim(rs.getString(2))));
				column.put("len", "");
				column.put("pri", StringUtils.trim(rs.getString(5)));
				System.out.println("type:"+Utils.getJavaType(StringUtils.trim(rs.getString(2)))+" pri:"+StringUtils.trim(rs.getString(5)));
				if ("PRI".equals(column.get("pri"))) {
					map.put("priType", Utils.getJavaType(StringUtils.trim(rs.getString(2))));
				}
				column.put("desc", StringUtils.trim(rs.getString(9)));
				for (int i = 0; i < j; i++) {
					// column.put("cc" + (i+1),
					// StringUtils.trim(rs.getString(i + 1)));

					Utils.prtln("        colname:" + StringUtils.trim(rs.getString(i + 1)));
					// 读action模版
					// Utils.prtln(rs.getString(1));
				}
			}
		}
		return list;
	}

	public static List<String> getTables() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<String> tbnames = new ArrayList<String>();
		try {
			con = getcon();
			stmt = con.createStatement();
			String showTables = "show tables";
			rs = stmt.executeQuery(showTables);
			ResultSetMetaData rsmd = rs.getMetaData();
			int j = rsmd.getColumnCount();
			// for (int k = 0; k < j; k++) {
			// System.out.print(rsmd.getCatalogName(k + 1));
			// System.out.print("\t");
			// }
			// Utils.prtln ();
			while (rs.next()) {
				for (int i = 0; i < j; i++) {
					tbnames.add(StringUtils.trim(rs.getString(i + 1)));
					// System.out.print(rs.getString(i + 1));
					// System.out.print("\t");
				}
				// Utils.prtln ();
			}
		} catch (SQLException e2) {
			Utils.prtln("数据库存在异常！");
			Utils.prtln(e2.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				Utils.prtln(e.toString());
			}
		}
		return tbnames;
	}

	private static Connection getcon() {
		String user = "root";
		String password = "cjteamDB";
		String url = "jdbc:mysql://182.92.66.201:3306/mydb?characterEncoding=utf8&&zeroDateTimeBehavior=converttonull";
		String driver = "com.mysql.jdbc.Driver";
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			Utils.prtln("数据库驱动不存在！");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
