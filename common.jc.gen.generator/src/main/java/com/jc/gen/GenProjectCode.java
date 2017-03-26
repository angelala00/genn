package com.jc.gen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.jc.schema.ITbinfoUtils;
import com.jc.schema.TbinfoMysqlUtils;
import com.jc.util.ConfigUtils;
import com.jc.util.Utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class GenProjectCode {

	private static String templateProjectDirPath = ConfigUtils.getConf().getString("mygenn.templateProjectDirPath");
	private static String targetProjectDirPath = ConfigUtils.getConf().getString("mygenn.targetProjectDirPath");
	private static String targetProjectName = ConfigUtils.getConf().getString("mygenn.targetProjectName");
	private static String targetProjectPackage = ConfigUtils.getConf().getString("mygenn.targetProjectPackage");
	private static String excludefiles = ConfigUtils.getConf().getString("mygenn.excludefiles");
	private static List<String> efs = new ArrayList<String>();
	static{
		for (String ef : excludefiles.split("\\|")) {
			if (StringUtils.isNotBlank(ef)) {
				efs.add(ef);
			}
		}
	}
	
	static List<Map<String, Object>> tbinfos_java;
	public void gen() throws Exception {
		ITbinfoUtils sqlUtil = new TbinfoMysqlUtils();
		List<Map<String, Object>> tbinfos = sqlUtil.getTbInfos();
		tbinfos_java = Utils.changeTbInfos(tbinfos);
		gennFileFromTemplateFile(templateProjectDirPath + "/" + "${projectName}");
	
	}
	// notice:tableName var can only use once, i will add this system later.
	// notice:just used in single key in db table.
	private static void gennFileFromTemplateFile(String filename) throws Exception {
		for (String ef : efs){
			if (filename.contains(ef)) {
				System.out.println("==excludefile:" + filename);
				return;
			}
		}
		File f = new File(filename);
		if (!f.exists()) {
			Utils.prtln("file " + filename + " is not exist");
			return;
		}
		if (!f.isDirectory()) {// if is file load it as ftl and genn file(think about projectnamt var and tablename var)
			Utils.prtln("this is a file : absolutepath:" + f.getAbsolutePath());
			if (f.getName().endsWith(".jpg") || f.getName().endsWith(".gif") || f.getName().endsWith(".png") || f.getName().endsWith(".woff") || f.getName().endsWith(".svg") || f.getName().endsWith(".ttf") || f.getName().endsWith(".eot") || f.getName().endsWith(".js")) {
				copyfile(f.getParentFile().getAbsolutePath(), f.getName());
			} else {
				genn(f.getParentFile().getAbsolutePath(), f.getName());
			}
		} else {// if dir genn dir (think about packagename var and projectname var)
			Utils.prtln("this is a directory : absolutepath:" + f.getAbsolutePath());
			String targetPath = replaceTemplatePathToTargetPath(f.getAbsolutePath());
			touchTargetPath(targetPath);
			for (String fn : f.list()) {
				gennFileFromTemplateFile(f.getAbsoluteFile() + "/" + fn);
			}
		}
	}
	private static void copyfile(String templateFileDirPath, String templateFileName) {
		String targetFilePath = replaceTemplatePathToTargetPath(templateFileDirPath);
		touchTargetPath(targetFilePath);
		String outfilename = targetFilePath +"/"+templateFileName;
		try {
			IOUtils.copy(new FileInputStream(templateFileDirPath + "/" + templateFileName), new FileOutputStream(outfilename));
		} catch (FileNotFoundException e) {
			System.out.println("file not fund");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("io exception");
			e.printStackTrace();
		}
	}
	private static void genn(String templateFileDirPath, String templateFileName) throws Exception {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("projectName", targetProjectName);
		root.put("targetPackage", targetProjectPackage);
		String outfilename;
		String targetFilePath = replaceTemplatePathToTargetPath(templateFileDirPath);
		touchTargetPath(targetFilePath);
		Configuration config = new Configuration();
		config.setDirectoryForTemplateLoading(new File(templateFileDirPath));
		config.setObjectWrapper(new DefaultObjectWrapper());
		Template template = config.getTemplate(templateFileName,"utf-8");
		if (templateFileName.contains("${tableName}") || templateFileName.contains("${TableName}")) {
			for (Map<String, Object> tbinfo : tbinfos_java) {
				root.put("tbinfo", tbinfo);
//				root.put("tbname", tbname);
//				root.put("Tbname", Tbname);
				String Tbname = (String) tbinfo.get("tbnameJavaType");
				String tbname = (String) tbinfo.get("tbnameJavaVar");
				root.put("currdate", new Date().toString());
				outfilename = targetFilePath +"/"+templateFileName.replaceAll("\\$\\{tableName\\}", tbname).replaceAll("\\$\\{TableName\\}", Tbname);
				writeOneFile(template, root, outfilename);
			}
		} else {
			root.put("tbinfos", tbinfos_java);
			outfilename = targetFilePath +"/"+templateFileName;
			writeOneFile(template, root, outfilename);
		}
	}
	
	private static void writeOneFile(Template template, Map<String, Object> varMap, String outfilename) throws Exception {
		System.out.println("gen file path:" + outfilename);
		Writer out = new OutputStreamWriter(new FileOutputStream(outfilename));
		template.process(varMap, out);
//		try {
//		} catch (Exception e) {
//			System.out.println();
//			e.printStackTrace();
//		}
		out.flush();
		out.close();
	}
	/**
	 * genn empty folder
	 * @param path
	 */
	private static void touchTargetPath(String path) {
		File targetFile = new File(path);
		if (!targetFile.exists()) {
			System.out.println("mkdirs path:"+path);
			targetFile.mkdirs();
		}
	}
	private static String replaceTemplatePathToTargetPath(String path) {
		return path.replaceAll(templateProjectDirPath, targetProjectDirPath).replaceAll("\\$\\{projectName\\}", targetProjectName).replaceAll("\\$\\{targetPackage\\}", targetProjectPackage.replaceAll("\\.", "/"));
	}
}
