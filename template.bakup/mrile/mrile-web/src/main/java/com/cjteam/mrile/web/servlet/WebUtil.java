package com.cjteam.mrile.web.servlet;


public class WebUtil {
	

	public long getCurrentTimeStemp(){
		return System.currentTimeMillis();
	}
//	private String projectname = "/mrile-manager";
	private String projectname = "";
	private String staticresourceshost = projectname + "/resources";
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
}
