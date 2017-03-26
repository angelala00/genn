package ${targetPackage}.web.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class WebUtil {

//	private String projectname = "/mrile-manager";
	private String projectname = "";
	private String staticresourceshost = projectname + "/resources";
	public static String formatDate(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	//把page参数去掉
	public static String removePageParam(String url){
		if (url.contains("?page=")) {
		} else if (url.contains("&page=")) {
		}
		return url.replaceAll("page=", "pagee=");
	}
	public String getStaticresourceshost() {
		return this.staticresourceshost;
	}
	public void setStaticresourceshost(String staticresourceshost) {
		this.staticresourceshost = staticresourceshost;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public static String getCurrentRequestUrl(HttpServletRequest request) {
		String strBackUrl = "http://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ request.getServletPath() + "?" + (request.getQueryString());
		return strBackUrl;
	}
}
