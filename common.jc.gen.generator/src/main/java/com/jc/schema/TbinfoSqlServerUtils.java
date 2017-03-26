package com.jc.schema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.jc.util.Tools;
import com.jc.util.Utils;

public class TbinfoSqlServerUtils implements ITbinfoUtils {

	public static void main(String[] args) throws Exception {
		List<Map<String, Object>> tbinfos = new TbinfoSqlServerUtils().getTbInfos();
		System.out.println(new Gson().toJson(tbinfos));
	}
	@Override
	public List<Map<String, Object>> getTbInfos() throws Exception {
        Map<String, Map<String, String>> allEntityFieldAndLableMap = Tools.parseFieldAndLableMap();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<String> tbnames = getTables();
        StringBuilder sbTmp = new StringBuilder();
		for (String tbname : tbnames) {
			Map<String, Object> map = new HashMap<String, Object>();
			list.add(map);
			Utils.prtln("   tbname:" + tbname);
			map.put("tbname", tbname);
			String showColumns = "SELECT syscolumns.name,systypes.name,syscolumns.isnullable,syscolumns.length FROM syscolumns, systypes WHERE syscolumns.xusertype = systypes.xusertype AND syscolumns.id = object_id('"+tbname+"')";;
			Utils.prtln(showColumns);
			con = getcon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(showColumns);
			List<Map<String, String>> columns = new ArrayList<Map<String, String>>();
			map.put("columns", columns);
			String pkColumnName = "";
			while (rs.next()) {
				Utils.prtln("==");
				Map<String, String> column = new HashMap<String, String>();
				columns.add(column);
				column.put("name", StringUtils.trim(rs.getString(1)));
				column.put("name_java", Utils.toFirstLowerCase(Utils.getEntityName(StringUtils.trim(rs.getString(1)))));
				column.put("name_Java", Utils.toFirstUpperCase(Utils.getEntityName(StringUtils.trim(rs.getString(1)))));
                if (allEntityFieldAndLableMap.containsKey(StringUtils.lowerCase(Utils.getEntityName(tbname))) ) {
                    if(allEntityFieldAndLableMap.get(StringUtils.lowerCase(Utils.getEntityName(tbname))).containsKey(StringUtils.lowerCase(column.get("name_java")))){
                        column.put("name_label", allEntityFieldAndLableMap.get(StringUtils.lowerCase(Utils.getEntityName(tbname)))
                                .get(StringUtils.lowerCase(column.get("name_java"))));
                    }else{
                        column.put("name_label", column.get("name_java"));
                        System.out.println("label not found : {entityName:" + Utils.getEntityName(tbname) + "," + tbname
                                + ",field:" + column.get("name_java")+ "," + column.get("name") + "}");
                        sbTmp.append(tbname).append("\n\t\t").append(column.get("name")).append("\n");
                    }
                } else {
                    column.put("name_label", column.get("name_java"));
                    System.out.println("entity map not found : {entityName:" + Utils.getEntityName(tbname));
                    sbTmp.append(tbname).append("\n\t\t").append(column.get("name")).append("\n");
                }
                column.put("type", StringUtils.trim(rs.getString(2)));
				column.put("type_java", Utils.getJavaType(StringUtils.trim(rs.getString(2))));
				column.put("desc", "");
				if (column.get("name").startsWith("pk")) {///////////////
					pkColumnName = column.get("name");
				}
			}
			map.put("pk", pkColumnName);
			map.put("pk_java", Utils.toFirstLowerCase(Utils.getEntityName(pkColumnName)));
			map.put("pk_Java", Utils.toFirstUpperCase(Utils.getEntityName(pkColumnName)));
		}

        System.out.println("需要汉化的内容：\n"+sbTmp.toString());
		return list;
	}

	private static List<String> getTables() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<String> tbnames = new ArrayList<String>();
		try {
			con = getcon();
			stmt = con.createStatement();
			String showTables = "select   * from   sysobjects   Where xtype='U'";
			rs = stmt.executeQuery(showTables);
			while (rs.next()) {
				if (
						StringUtils.trim(rs.getString(1)).startsWith("code")
						|| StringUtils.trim(rs.getString(1)).startsWith("Code")
						|| StringUtils.trim(rs.getString(1)).startsWith("train")
						|| StringUtils.trim(rs.getString(1)).startsWith("Train")
						|| StringUtils.trim(rs.getString(1)).startsWith("Public_Enterprise")
						|| StringUtils.trim(rs.getString(1)).startsWith("Public_Staff")
						) {
					tbnames.add(StringUtils.trim(rs.getString(1)));
				}
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
		String user = "hrpsp";
		String password = "hr@psp1234";
		String url = "jdbc:jtds:sqlserver://49.4.144.167/HRPSP;integratedSecurity=TRUE;sendStringParametersAsUnicode=TRUE";
		String driver = "net.sourceforge.jtds.jdbc.Driver";
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
