package com.cjteam.mrile.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.mrile.api.controller.vo.VersionResponseVo;
import com.cjteam.mrile.service.AppClientService;

@Controller
@RequestMapping("")
public class VersionController extends BaseController {

	@Autowired
	private AppClientService appClientService;
	@Value("${mrile.versionNum}")
	private String versionNum;

	@RequestMapping(value = { "/version/hasnew" })
	public @ResponseBody VersionResponseVo hasNewVersion() {
		VersionResponseVo response = new VersionResponseVo();
		try {
			response.setUrl("http://");
			response.setVersionNum(versionNum);
			response.setPolicy(0);
			response.setSuccess(Boolean.TRUE);
			response.setMessage("");
		} catch (Throwable t) {
			logger.error(t.getLocalizedMessage(), t);
			response.setMessage(t.getLocalizedMessage());

		}
		return response;
	}

}
