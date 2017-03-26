package com.cjteam.mrile.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.mrile.api.controller.vo.ApiPageVo;
import com.cjteam.mrile.api.controller.vo.ExchangeRecordListContentVo;
import com.cjteam.mrile.api.controller.vo.ResponseExtensionVo;
import com.cjteam.mrile.model.ExchangeRecord;
import com.cjteam.mrile.service.ExchangeRecordService;
import com.cjteam.mrile.web.Pager;

@Controller
@RequestMapping("/exchangeRecord")
public class ExchangeRecordController extends BaseController {
	@Autowired
	private ExchangeRecordService exchangeRecordService;

	@RequestMapping("list")
	public @ResponseBody ResponseExtensionVo<ApiPageVo<ExchangeRecordListContentVo>> list(
			@ModelAttribute Pager pageinfo, String userId) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),
				pageinfo.getSize());
		ApiPageVo<ExchangeRecordListContentVo> apiPageVo = new ApiPageVo<ExchangeRecordListContentVo>();
		ResponseExtensionVo<ApiPageVo<ExchangeRecordListContentVo>> responseExtensionVo = new ResponseExtensionVo<ApiPageVo<ExchangeRecordListContentVo>>();
		try {
			Page<ExchangeRecord> exchangeRecordPage = exchangeRecordService
					.getListByUserId(pageableObj, userId);
			apiPageVo.setPage(exchangeRecordPage);
			ExchangeRecordListContentVo exchangeRecordListContentVo;
			List<ExchangeRecordListContentVo> exchangeRecordListContentVoList = new ArrayList<ExchangeRecordListContentVo>();
			for (ExchangeRecord exchangeRecord : exchangeRecordPage
					.getContent()) {
				exchangeRecordListContentVo = new ExchangeRecordListContentVo();
				exchangeRecordListContentVo.setContent(exchangeRecord
						.getContent());
				exchangeRecordListContentVo.setCreateTime(exchangeRecord
						.getCreateTime().toString().substring(0, 19));
				exchangeRecordListContentVo.setSurplus(exchangeRecord
						.getSurplus());
				exchangeRecordListContentVoList
						.add(exchangeRecordListContentVo);
			}
			apiPageVo.setContent(exchangeRecordListContentVoList);
			responseExtensionVo.setContent(apiPageVo);
		} catch (Exception e) {
			e.printStackTrace();
			responseExtensionVo.setMessage(e.getMessage());
			responseExtensionVo.setSuccess(Boolean.FALSE);
		}
		return responseExtensionVo;
	}
}
