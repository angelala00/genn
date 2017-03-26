package com.gj.course.task.executor.spider;

import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.gj.course.model.Course;
import com.gj.course.service.CourseService;
import com.gj.course.task.HttpTools;
import com.google.gson.Gson;

public class Imooc {
	@Autowired
	private CourseService courseService;

	public void runimooc(){
		HttpTools http = new HttpTools();
		String s = http
				.getContentFromUrlByGet("http://www.imooc.com/course/list");
		Document d = Jsoup.parse(s);
		Elements es = d
				.select(".course-sidebar>.course-sidebar-type>ul>li.course-category.course-category-more");
		for (Element e : es) {
			String name = e.select(">a").text();
			System.out.println("name:" + name);
			Elements catesubs = e.select("div>ul>li>a");
			for (Element ee : catesubs) {
				String subname = ee.text();
				String href = ee.attr("href");
				String dataid = ee.attr("data-id");
				System.out.println("\tsubname:" + subname);
				String subcateurl = "http://www.imooc.com/course/ajaxlist?cat_id=0&lange_id="
						+ dataid + "&sort=last&pagesize=30&unlearn=0&page=1";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println(subcateurl);
				String catecontent = http.getContentFromUrlByGet(subcateurl);
				Map s1 = new Gson().fromJson(catecontent, Map.class);
				List<Object> ss = (List<Object>) s1.get("list");
				for (Object object : ss) {
					System.out.println(object);
					Map objMap = (Map) object;
					String namee = (String) objMap.get("name");
					System.out.println("namee:" + namee);
					String wid = (String) objMap.get("id");
					String nameee = (String) objMap.get("name");
					String pic = (String) objMap.get("pic");
					String desc = (String) objMap.get("short_description");
					Course c = new Course();
					c.setWebsiteId(wid);
					c.setName(nameee);
					c.setCoverName(pic);
					c.setDescription(desc);
					System.out.println(c);
					courseService.save(c);
				}

//				Document subcontent = Jsoup.parse(catecontent);
//				Elements subcs = subcontent
//						.select(".course-content>.course-list>div>ul>li");
//				for (Element sub : subcs) {
//
//					String href1 = sub.select("a").attr("href");
//					String name1 = sub.select("a>.intro>p").text();
//					System.out.println(href1 + ":" + name1);


//				}

			}
		}
	
		
	}
}
