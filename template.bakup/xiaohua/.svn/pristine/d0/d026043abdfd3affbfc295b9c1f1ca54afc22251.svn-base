package com.cjteam.xiao.web.controller.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjteam.xiao.service.QueueService;

/**
 * 队列
 */
@Controller
@RequestMapping("/admin")
public class QueueController {
	@Resource
	private QueueService queueService ; 
	private final static String  INDEX  = "/admin/queue/index" ;

    /**
     * 队列列表
     */
	@RequestMapping(value = "queue")
	public String index(Map<String,Object> map){
        //队列列表
		map.put("list",this.queueService.getList()) ;
		return this.INDEX ;
	}
}
