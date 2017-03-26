package com.cjteam.mrile.task.executor.spider;

import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjteam.mrile.model.XiaohuaContent;
import com.cjteam.mrile.service.XiaohuaContentService;
import com.cjteam.mrile.task.HttpTools;

@Service
public class PengFu {
	@Autowired
	private XiaohuaContentService xiaohuaContentService;
	public void runpengfu(){
		HttpTools http = new HttpTools();
		String s = http
				.getContentFromUrlByGet("http://www.pengfu.com/xiaohua_1.html");
		Document d = Jsoup.parse(s);
		Elements es = d.select(".tieziBox");
		for (Element e : es) {
			String tieTitle = e.select(">.contFont>.tieTitle>a").text();
			Element fdiv = e.select(">.contFont>.imgbox>div").first();
			String tid = "0";
			try {
				tid = fdiv.attr("id").split("_")[1];
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String content = fdiv .text();
			XiaohuaContent xc = new XiaohuaContent();
			xc.setBad(0);
			xc.setGood(0);
			xc.setContent(content);
			xc.setCreateTime(new Date());
			xc.setSort(0);
			xc.setTitle(tieTitle);
			xc.setType("text");
			xc.setSort(0);
			xc.setTargetId(tid);
			xiaohuaContentService.save(xc);
		}
	}
}
