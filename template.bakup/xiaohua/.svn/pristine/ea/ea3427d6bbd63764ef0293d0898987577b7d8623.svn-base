package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.model.Announcement;
import com.cjteam.xiao.model.ExchangeRecord;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.ExchangeRecordService;
import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.web.vo.AnnouncementListContentVo;
import com.cjteam.xiao.web.vo.ApiPageVo;
import com.cjteam.xiao.web.vo.ExchangeRecordListContentVo;
import com.cjteam.xiao.web.vo.ResponseExtensionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlong on 2014/8/13.
 */
@Controller
@RequestMapping("/exchangeRecord")
public class ExchangeRecordController {
    @Autowired
    private ExchangeRecordService exchangeRecordService;
    @ResponseBody
    @RequestMapping("list")
    public ResponseExtensionVo list(PageBasicInfo pageBasicInfo , String userId){
        ApiPageVo<ExchangeRecordListContentVo> apiPageVo = new ApiPageVo<ExchangeRecordListContentVo>() ;
        ResponseExtensionVo<ApiPageVo<ExchangeRecordListContentVo>> responseExtensionVo  = new ResponseExtensionVo<ApiPageVo<ExchangeRecordListContentVo>>() ;
        try {
            Page<ExchangeRecord> exchangeRecordPage =  this.exchangeRecordService.getListByUserId(pageBasicInfo ,userId ) ;
            apiPageVo.setPage(exchangeRecordPage);
            ExchangeRecordListContentVo exchangeRecordListContentVo ;
            List<ExchangeRecordListContentVo> exchangeRecordListContentVoList = new ArrayList<ExchangeRecordListContentVo>() ;
            for(ExchangeRecord exchangeRecord:exchangeRecordPage.getContent()){
                exchangeRecordListContentVo = new ExchangeRecordListContentVo() ;
                exchangeRecordListContentVo.setContent(exchangeRecord.getContent());
                exchangeRecordListContentVo.setCreateTime(exchangeRecord.getCreateTime().toString().substring(0,19));
                exchangeRecordListContentVo.setSurplus(exchangeRecord.getSurplus());
                exchangeRecordListContentVoList.add(exchangeRecordListContentVo);
            }
            apiPageVo.setContent(exchangeRecordListContentVoList);
            responseExtensionVo.setContent(apiPageVo);
        }
        catch (Exception e){
            e.printStackTrace();
            responseExtensionVo.setMessage(e.getMessage());
            responseExtensionVo.setSuccess(Boolean.FALSE);
        }
        return responseExtensionVo ;
    }
}
