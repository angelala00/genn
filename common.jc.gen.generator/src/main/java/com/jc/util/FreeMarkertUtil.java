package com.jc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkertUtil {
	static String temppath = ConfigUtils.getConf().getString("conf.templatepath");
	
	public static void analysisTemplate(String templateName,
			String templateEncoding, Map<?, ?> root, String path, String actionName, String suffixes) {
		try {
			Configuration config = new Configuration();
			File file = new File(temppath);
			/**
			 * 设置要解析的模板所在的目录，并加载模板文件
			 */
			config.setDirectoryForTemplateLoading(file);
			config.setObjectWrapper(new DefaultObjectWrapper());
			Template template = config.getTemplate(templateName,
					templateEncoding);
			String fileAbPath = path + root.get(actionName) + "." + suffixes;
			Writer out = new OutputStreamWriter(new FileOutputStream(fileAbPath));
			template.process(root, out);
			out.flush();
			out.close();
			System.out.println("file "+ fileAbPath+" created successfully");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
