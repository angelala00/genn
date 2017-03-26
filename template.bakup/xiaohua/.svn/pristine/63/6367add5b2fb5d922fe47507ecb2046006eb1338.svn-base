package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.nosql.NosqlListUtil;
import com.cjteam.xiao.service.QueueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class QueueServiceImpl implements QueueService {
	@Resource
	private NosqlListUtil nosqlListUtil ;
	private final static String QUEUE_KEY = "queue" ;

	public List<String> getList(){
		return this.nosqlListUtil.getList(this.QUEUE_KEY) ;
	}
}