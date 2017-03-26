package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.model.Announcement;
import com.cjteam.xiao.service.AnnouncementService;
import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.web.vo.AnnouncementListContentVo;
import com.cjteam.xiao.web.vo.ApiPageVo;
import com.cjteam.xiao.web.vo.ResponseExtensionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlong on 2014/7/30.
 */
@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService ;
    @ResponseBody
    @RequestMapping("getOne")
    public ResponseExtensionVo getOne(@RequestParam(required=false) int id){
        ResponseExtensionVo<String> responseExtensionVo = new ResponseExtensionVo<String>() ;
        try {
            Announcement announcement ;
            if (id!=0){
                announcement =  this.announcementService.get(id) ;
            }
            else{
                announcement = this.announcementService.getRecommend() ;
            }
            if (announcement!=null){
                responseExtensionVo.setContent(announcement.getContent());
            }
            else{
                throw new RuntimeException("没有公告");
            }
        }catch (Exception e){
            e.printStackTrace();
            responseExtensionVo.setSuccess(Boolean.FALSE);
            responseExtensionVo.setMessage(e.getMessage());
        }
        return responseExtensionVo ;
    }
    @ResponseBody
    @RequestMapping("list")
    public ResponseExtensionVo list(PageBasicInfo pageBasicInfo){
        ResponseExtensionVo<ApiPageVo> responseExtensionVo  = new ResponseExtensionVo<ApiPageVo>() ;
        try {
            Page<Announcement> announcementPage =  this.announcementService.getList(pageBasicInfo) ;
            ApiPageVo apiPageVo = new ApiPageVo<AnnouncementListContentVo>() ;
            apiPageVo.setPage(announcementPage);
            AnnouncementListContentVo announcementListContentVo ;
            List<AnnouncementListContentVo> announcementListContentVoList = new ArrayList<AnnouncementListContentVo>() ;
            for(Announcement announcement:announcementPage.getContent()){
                announcementListContentVo = new AnnouncementListContentVo() ;
                announcementListContentVo.setTitle(announcement.getTitle());
                announcementListContentVo.setId(announcement.getId());
                announcementListContentVo.setCreateTime(announcement.getCreateTime().toString().substring(0,10));
                announcementListContentVoList.add(announcementListContentVo);
            }
            apiPageVo.setContent(announcementListContentVoList);
            responseExtensionVo.setContent(apiPageVo);
        }
        catch (Exception e){
            responseExtensionVo.setMessage(e.getMessage());
            responseExtensionVo.setSuccess(Boolean.FALSE);
        }
        return responseExtensionVo ;
    }
}
