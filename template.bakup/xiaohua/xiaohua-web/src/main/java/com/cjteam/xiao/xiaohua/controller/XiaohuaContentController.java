package com.cjteam.xiao.xiaohua.controller;

import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.web.vo.ResponseExtensionVo;
import com.cjteam.xiao.xiaohua.model.XiaohuaContent;
import com.cjteam.xiao.xiaohua.service.XiaohuaContentService;
import com.cjteam.xiao.xiaohua.vo.XiaohuaContentListContentVo;
import com.cjteam.xiao.xiaohua.vo.XiaohuaContentListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlong on 2014/7/29.
 */
@Controller
@RequestMapping("/xiaohuaContent")
public class XiaohuaContentController {
    @Autowired
    private XiaohuaContentService xiaohuaContentService ;
    @Value("${PICBasicPath}")
    private String PICBasicPath;
    @Value("${PICUrlBasic}")
    private String PICUrlBasic ;
    @ResponseBody
    @RequestMapping(value = "list")
    public ResponseExtensionVo list(String type , PageBasicInfo pageBasicInfo){
        ResponseExtensionVo responseExtensionVo = new ResponseExtensionVo<XiaohuaContentListContentVo>() ;
        try {
            Page<XiaohuaContent> xiaohuaContentPage = this.xiaohuaContentService.getByType(type, pageBasicInfo);
            XiaohuaContentListVo xiaohuaContentListVo = new XiaohuaContentListVo();
            xiaohuaContentListVo.setPage(xiaohuaContentPage);
            XiaohuaContentListContentVo xiaohuaContentListContentVo;
            List<XiaohuaContentListContentVo> xiaohuaContentListContentVoList = new ArrayList<XiaohuaContentListContentVo>();
            File file ;
            String pic  ;
            InputStream is = null;
            BufferedImage src = null;
            int width  = 0 ;
            int height = 0 ;
            for (XiaohuaContent xiaohuaContent : xiaohuaContentPage.getContent()) {
                xiaohuaContentListContentVo = new XiaohuaContentListContentVo();
                pic  = xiaohuaContent.getPic() ;
                if (pic!=null) {
                    try {
                        file = new File(this.PICBasicPath + pic);
                        if (file.exists())
                            is = new FileInputStream(file);
                        src = ImageIO.read(is);
                        xiaohuaContentListContentVo.setWide(src.getWidth()); // 得到源图宽
                        xiaohuaContentListContentVo.setHigh(src.getHeight());
                        is.close();
                    }
                    catch (Exception e){
                        System.out.println("++++++++++++++++++++"+this.PICBasicPath + pic+"----------------");
                        e.printStackTrace();
                    }
                }
                xiaohuaContentListContentVo.setTitle(xiaohuaContent.getTitle());
                xiaohuaContentListContentVo.setBad(xiaohuaContent.getBad());
                xiaohuaContentListContentVo.setContent(xiaohuaContent.getContent());
                xiaohuaContentListContentVo.setGood(xiaohuaContent.getGood());
                xiaohuaContentListContentVo.setId(xiaohuaContent.getId());
                xiaohuaContentListContentVo.setPic(this.PICUrlBasic+xiaohuaContent.getPic());
                xiaohuaContentListContentVoList.add(xiaohuaContentListContentVo);
            }
            xiaohuaContentListVo.setContent(xiaohuaContentListContentVoList);

            responseExtensionVo.setContent(xiaohuaContentListVo);
        }catch (Exception e){
            e.printStackTrace();
            responseExtensionVo.setMessage(e.getMessage());
            responseExtensionVo.setSuccess(Boolean.FALSE);
        }
        return responseExtensionVo ;
    }
    @ResponseBody
    @RequestMapping(value = "clickGood")
    public String  clickGood(int id ,@RequestParam(required = false)  String userId){
        this.xiaohuaContentService.clickGood(id , userId);
        return  "" ;
    }
    @ResponseBody
    @RequestMapping(value = "clickBad")
    public String clickBad(int id){
        this.xiaohuaContentService.clickBad(id);
        return "" ;
    }

}
