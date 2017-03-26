package com.cjteam.xiao.xiaohua.spider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TaskMain {
	
	public static void main(String args[]) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		TaskImpl taskImpl = (TaskImpl)ac.getBean(TaskImpl.class);
		System.out.println("====please input param");
		taskImpl.spider(TaskImpl.SpiderConfig.PENGFU);
		taskImpl.spider(TaskImpl.SpiderConfig.ZOL);
		taskImpl.spider(TaskImpl.SpiderConfig.ZOL_PIC);
		taskImpl.spider(TaskImpl.SpiderConfig.PENGFU_PIC);
	}
}
