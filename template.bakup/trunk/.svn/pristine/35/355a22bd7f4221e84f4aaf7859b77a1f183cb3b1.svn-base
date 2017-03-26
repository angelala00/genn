package com.cjteam.mrile.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.mrile.api.controller.vo.ChannelsResponseVo;
import com.cjteam.mrile.api.controller.vo.ChannelsVo;
import com.cjteam.mrile.model.Channel;
import com.cjteam.mrile.service.AnnouncementService;
import com.cjteam.mrile.service.ChannelService;

@Controller
public class ChannelController extends BaseController {
	@Autowired
	private AnnouncementService announcementService;

	@RequestMapping("/channels")
	public @ResponseBody ChannelsResponseVo channels(HttpServletRequest request) {
		ChannelsResponseVo response = new ChannelsResponseVo();
		try {
			String clientIp = request.getHeader("X-Real-IP");
			List<Channel> channels = channelService.getValidChannels();
			if (channels != null && channels.size() > 0) {
				List<ChannelsVo> sssss = change(channels);
				response.setChannels(sssss );
				response.setSuccess(Boolean.TRUE);
				response.setCount(sssss.size());
			} else {
				response.setMessage("no channel data");
			}
		} catch (Throwable throwable) {
			response.setMessage(throwable.getMessage());
			logger.error(throwable.getLocalizedMessage(), throwable);
		}
		return response;
	}

	private List<ChannelsVo> change(List<Channel> channels) {
		List<ChannelsVo> s = new ArrayList<ChannelsVo>();
		for (Channel c : channels) {
			s.add(new ChannelsVo(c));
		}
		return s;
	}

	@Autowired
	private ChannelService channelService;

}
