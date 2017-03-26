package com.gj.course.task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gj.course.task.executor.spider.Imooc;

public class App {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/spring/spring-application-context.xml");
		Imooc im = ac.getBean(Imooc.class);
		im.runimooc();
	}
}
