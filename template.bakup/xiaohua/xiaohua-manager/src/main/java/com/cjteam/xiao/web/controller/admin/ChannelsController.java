package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.model.Channel;
import com.cjteam.xiao.service.ChannelService;
import com.cjteam.xiao.util.PageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ChenLong
 * Date: 13-11-1
 */
@Controller("adminChannelsController")
@RequestMapping("/admin/channels")
@PreAuthorize(
        "hasRole('admin') ")
public class ChannelsController {
    private static final Logger log = LoggerFactory.getLogger(ChannelsController.class);

    @RequestMapping(value = {"/list", "/", "/all",""})
    public String list(@RequestParam(value = "appId", required = false) String appId,ModelMap map) {
        log.info("list data");
        map.put(PageConstant.VIEW_LIST, channelService.getAllChannels(appId).getContent());
        return "/admin/channels/list";
    }

    @RequestMapping("/view/{id:\\w+}")
    public String view(@PathVariable Integer id, ModelMap map) {
        log.info("view id {} ", id);
        map.put(PageConstant.VIEW_ENTITY, channelService.get(id));
        return "/admin/channels/detail";
    }
    @RequestMapping("/close/{id:\\w+}")
    public String close(@PathVariable Integer id, ModelMap map) {
        log.info("close id {} ", id);
        channelService.closeChannel(id);
        return "redirect:/admin/channels/list";
    }

    @RequestMapping("/open2/{id:\\w+}")
    public String open2(@PathVariable Integer id, ModelMap map) {
        log.info("open id {} ", id);
        channelService.open2Channel(id);
        return "redirect:/admin/channels/list";
    }
    @RequestMapping("/close2/{id:\\w+}")
    public String close2(@PathVariable Integer id, ModelMap map) {
        log.info("close id {} ", id);
        channelService.close2Channel(id);
        return "redirect:/admin/channels/list";
    }
    @RequestMapping("/open/{id:\\w+}")
    public String open(@PathVariable Integer id, ModelMap map) {
        log.info("open id {} ", id);
        channelService.openChannel(id);
        return "redirect:/admin/channels/list";
    }

    @RequestMapping("/edit/{id:\\d+}")
    public String edit(@PathVariable Integer id, ModelMap map) {
        log.info("channel edit {}", id);
        map.put(PageConstant.VIEW_ENTITY, channelService.get(id));
        return "/admin/channels/edit";
    }

    @RequestMapping("/update")
    public String update(Channel channel, ModelMap map) {
        log.info("update channel");
        log.debug("{}", channel);
        channelService.save(channel);
        return "redirect:/admin/channels/list";
    }

    @Autowired
    private ChannelService channelService;
}
