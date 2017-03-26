package com.cjteam.xiao.web.controller.admin;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjteam.xiao.util.GifUtil;
import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.util.PageConstant;
import com.cjteam.xiao.xiaohua.model.XiaohuaContent;
import com.cjteam.xiao.xiaohua.service.XiaohuaContentService;

import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/admin/xiaohua")
public class XiaohuaController {
	private final static String INDEX = "/admin/xiaohua/index";
	private final static String ADD = "/admin/xiaohua/add";
	@Value("${SpiderPICRootPath}")
    private String spiderPICRootPath ;  
    @Value("${SpiderPICPath}")
    private String spiderPICPath ;
    @Autowired
    private GifUtil gifUtil  ;
	@Resource
	public XiaohuaContentService xiaohuaContentService ;
	@RequestMapping(value = {""})
	public  String index(PageBasicInfo pageBasicInfo , String title , Map<String, Object> map){
		map.put("page", this.xiaohuaContentService.getByTitle(title, pageBasicInfo)) ; 
		return this.INDEX ; 
	}
	@RequestMapping(value = {"/add"})
	public String add(){
		return this.ADD ;
	}
	 @RequestMapping(value = "/update/{id:\\d+}")
	public String update(@PathVariable(value = "id") int id , Map<String, Object> map){
		XiaohuaContent xiaohuaContent = this.xiaohuaContentService.get(id) ;
		if (xiaohuaContent!=null){
			map.put(PageConstant.VIEW_ENTITY, xiaohuaContent);
		}
		return this.ADD;
	}
	@RequestMapping(value = {"/subAdd"} , method = RequestMethod.POST)
	public String subAdd(XiaohuaContent xiaohuaContent  ,  @RequestParam("pica") MultipartFile pica){
		String picString  = this.uploadPic(pica) ; 
		if (picString!=null){
			xiaohuaContent.setPic(picString);
		}
		String tmppicString    = xiaohuaContent.getPic() ;
		if (tmppicString !=null && !"".equals(tmppicString)){
			xiaohuaContent.setType(XiaohuaContent.Type.image.toString());
		}
		else{
			xiaohuaContent.setType(XiaohuaContent.Type.text.toString());
		}
	
		this.xiaohuaContentService.save(xiaohuaContent);
		if (xiaohuaContent.getId()!=null){
			return "redirect:/admin/xiaohua "; 
		}
		return XiaohuaController.ADD ;
	}
	public String uploadPic(MultipartFile pica){
		if (pica.getSize() ==0){
			return null ;
		}
		String dbPic =null  ;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
			Date currentTime = new Date();
			String  fileBase = this.spiderPICPath+formatter.format(currentTime) ;
			String[] tmp  = pica.getOriginalFilename().split("\\.");
			String suffix = tmp[tmp.length-1] ; 
	        String  fileName = System.currentTimeMillis()+"." +suffix ;
	        String picPath = this.spiderPICRootPath+"\\"+fileBase ;
	        File sf = new File(picPath);
	        if (!sf.exists()) {
	            sf.mkdirs();
	        }
	    	File pic = new File(picPath+"\\"+fileName);
	        InputStream iis = null;
			iis = pica.getInputStream();
	        BufferedImage srcImg = null;
			srcImg = ImageIO.read(iis);
	        int newHeight   =(int)(srcImg.getHeight()/ (srcImg.getWidth()/310.00000000000000)) ;
	        char tmpa = suffix.charAt(0) ;
	        pica.transferTo(pic);
	        if (tmpa=='g' || tmpa=='G'){
				this.gifUtil.makeGif(pic , pic , 310 , newHeight);
	        }else {
	            BufferedImage buffImg = null;
	            buffImg = new BufferedImage(310, newHeight, BufferedImage.TYPE_INT_RGB);
	            buffImg.getGraphics().drawImage(srcImg.getScaledInstance(310, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
				ImageIO.write(buffImg, suffix, pic);
	        }
			
			dbPic = "/"+(fileBase+"/"+fileName).replaceAll("\\\\","/") ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbPic ;
	}
}
