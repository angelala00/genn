package com.cjteam.mrile.task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cjteam.mrile.task.executor.spider.PengFu;


public class MainMain {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/spring/spring-application-context.xml");
		PengFu pf = ac.getBean(PengFu.class);
		pf.runpengfu();
	}
}
