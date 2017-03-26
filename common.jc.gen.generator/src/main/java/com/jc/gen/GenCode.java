package com.jc.gen;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.jc.schema.ITbinfoUtils;
import com.jc.schema.TbinfoMysqlUtils;
import com.jc.util.ConfigUtils;
import com.jc.util.FreeMarkertUtil;
import com.jc.util.Utils;

public class GenCode {

	public static String actionPackage = ConfigUtils.getConf().getString("conf.actionPackage");
	private static String beanPackage = ConfigUtils.getConf().getString("conf.beanPackage");
	public static String separator = ConfigUtils.getConf().getString("sys.separator");
	
	public void gen() throws Exception {
		ITbinfoUtils sqlUtil = new TbinfoMysqlUtils();
		List<Map<String, Object>> tbinfos = sqlUtil.getTbInfos();
		List<Map<String, Object>> tbinfos_java = Utils.changeTbInfos(tbinfos);
		Map<String, List<Map<String, Object>>> getTbInfos_category_table_column = getTbInfos_category_table_column(tbinfos_java);

		Utils.prtln(new Gson().toJson(getTbInfos_category_table_column));
		for (String categoryName : getTbInfos_category_table_column.keySet()) {
			List<Map<String, Object>> tbinfos_tmp = getTbInfos_category_table_column
					.get(categoryName);
			String targetPath = ConfigUtils.getConf().getString("conf.targetPath");
			String javaPath = ConfigUtils.getConf().getString("conf.javaPath");
			String jspPath = ConfigUtils.getConf().getString("conf.jspPath");
			String actionPackagePath = actionPackage.replace(".", separator) + separator;
			String beanPackagePath = beanPackage.replace(".", separator) + separator;
//			String beanPath = targetPath + javaPath + beanPackagePath + categoryName + separator;
			String beanPath = targetPath + javaPath + beanPackagePath;
			//======================================action category path============================
//			String actionPath = targetPath + javaPath + actionPackagePath + categoryName + separator;
			String actionPath = targetPath + javaPath + actionPackagePath;
			//======================================jsp category path============================
			String jspPath_ = targetPath + jspPath + categoryName + separator;
			File jspPath_File = new File(jspPath_);
			if (!jspPath_File.isDirectory()) {
				jspPath_File.mkdir();
			}
			Utils.prtln("create dir " + jspPath_);
			int i = 1;
			for (Map<String, Object> tbinfo : tbinfos_tmp) {
				String tbname = (String) tbinfo.get("tbname");
				String beanName = Utils.getEntityName(tbname);
				Utils.prtln("create java file" + beanPath + beanName + ".java");
				String actionName = Utils.getActionName(tbname);
				Utils.prtln("create action file" + actionPath + actionName + ".java");
				Utils.prtln("open:" + actionPath + actionName + ".java");
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("tbinfo", tbinfo);
				root.put("actionPackage", actionPackage);
				root.put("beanPackage", beanPackage);
				root.put("categoryName", categoryName);
				root.put("beanName", Utils.toFirstLowerCase(beanName));
				root.put("BeanName", beanName);
				root.put("actionName", actionName);
				root.put("ActionName", Utils.toFirstUpperCase(actionName));
				root.put("currdate", new Date().toString());
//				printConfig(root, i++);
//				genAction(root, actionPath);
//				genJsp(root,jspPath_,tbname);
				genBean(root, beanPath);
			}
		}
	}

	private void genBean(Map<String, Object> root, String beanPath) {
		Utils.prtln("create dir " + beanPath);
		File actionPathFile = new File(beanPath);
		if (!actionPathFile.isDirectory()) {
			actionPathFile.mkdirs();
		}
		//======================================action============================
		FreeMarkertUtil.analysisTemplate("code_bean.ftl", "utf-8", root, beanPath, "beanName", "java");
	}

	private void genJsp(Map<String, Object> root, String jspPath_, String tbname) {
		//======================================jsp target path============================
		String jspName = Utils.getJspName(tbname);
		Utils.prtln("create jsp dir " + jspPath_ + jspName + separator);
		
		File jspPath_FilePath = new File(jspPath_ + jspName + separator);
		if (!jspPath_FilePath.isDirectory()) {
			jspPath_FilePath.mkdir();
		}
		
		//======================================jsp list============================
		Utils.prtln("create jsp file " + jspPath_ + jspName + separator + "list.jsp");
		root.put("listjspName", root.get("beanName") + "List");
		FreeMarkertUtil.analysisTemplate("list.ftl", "utf-8", root, jspPath_ + jspName + separator, "listjspName", "jsp");
		
		//======================================jsp add============================
		Utils.prtln("create jsp file " + jspPath_ + jspName + separator + "add.jsp");
		root.put("addjspName", root.get("beanName") + "Add");
		FreeMarkertUtil.analysisTemplate("add.ftl", "utf-8", root, jspPath_ + jspName + separator, "addjspName", "jsp");
		
		//======================================jsp edit============================
		Utils.prtln("create jsp file " + jspPath_ + jspName + separator + "edit.jsp");

		String actionPackageeeee = Utils.getActionPackage(tbname, actionPackage);
		// 替换
		// String jspPath = getJspPath(tbname);
		// 创建jsp文件
	}

	private void genAction(Map<String, Object> root, String actionPath) {
		Utils.prtln("create dir " + actionPath);
		File beanPathFile = new File(actionPath);
		if (!beanPathFile.isDirectory()) {
			beanPathFile.mkdirs();
		}
		//======================================action============================
		FreeMarkertUtil.analysisTemplate("code_action.ftl", "utf-8", root, actionPath, "actionName", "java");
	}

	private void printConfig(Map<String, Object> root, int index) {
		//======================================spring conf============================
		System.out.println("<bean id=\"" + root.get("ActionName") + "\" class=\"" + actionPackage + "." + root.get("categoryName") + "." + root.get("ActionName") + "\"></bean>");
		//======================================struts conf============================
		System.out.println("		<action name=\"" + root.get("ActionName") + "\" class=\"" + root.get("ActionName") + "\">");
		System.out.println("			<result name=\"list\" type=\"redirect\">/" + root.get("ActionName") + "!list.action</result>");
		System.out.println("			<result name=\"listjsp\" type=\"dispatcher\">/jsp/" + root.get("categoryName") + "/" + root.get("beanName") + "/" + root.get("beanName") + "List.jsp</result>");
		System.out.println("			");
		System.out.println("			<result name=\"toadd\" type=\"redirect\">/" + root.get("ActionName") + "!toadd.action</result>");
		System.out.println("			<result name=\"addjsp\" type=\"dispatcher\">/jsp/" + root.get("categoryName") + "/" + root.get("beanName") + "/" + root.get("beanName") + "Add.jsp</result>");
		System.out.println("			<result name=\"add\" type=\"redirect\">/" + root.get("ActionName") + "!add.action</result>");
		System.out.println("			");
		System.out.println("			<result name=\"edit\" type=\"redirect\">/" + root.get("ActionName") + "!edit.action</result>");
		System.out.println("			<result name=\"editjsp\" type=\"dispatcher\">/jsp/" + root.get("categoryName") + "/" + root.get("beanName") + "/" + root.get("beanName") + "Edit.jsp</result>");
		System.out.println("			<result name=\"save\" type=\"redirect\">/" + root.get("ActionName") + "!save.action</result>");
		System.out.println("			");
		System.out.println("		</action>");
		//======================================menu tree============================
		System.out.println("		folderTree1010111" + Utils.formatNumber(index) + " = insDoc(folderTree1010111, gLnk('0','&nbsp;" + root.get("beanName") + "', '<%=request.getContextPath()%>/" + root.get("ActionName") + "!list.action', 'ftv2link.gif'));");

	}

	private static Map<String, List<Map<String, Object>>> getTbInfos_category_table_column(
			List<Map<String, Object>> tbinfos) {
		Map<String, List<Map<String, Object>>> tempmap = new HashMap<String, List<Map<String, Object>>>();
		for (Map<String, Object> tbinfo : tbinfos) {
			String tbname = (String) tbinfo.get("tbname");
			String categoryName = Utils.getCategoryName(tbname);
			List<Map<String, Object>> list = null;
			if (tempmap.containsKey(categoryName)) {
				list = tempmap.get(categoryName);
			} else {
				list = new ArrayList<Map<String, Object>>();
				tempmap.put(categoryName, list);
			}
			list.add(tbinfo);
		}
		return tempmap;
	}

}
