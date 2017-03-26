package com.cjteam.xiao.xiaohua.service.impl;

import com.cjteam.xiao.dao.DailyRegionIntegralLimitDao;
import com.cjteam.xiao.dao.XiaohuaContentDao;
import com.cjteam.xiao.model.Channel;
import com.cjteam.xiao.model.Integral;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.repository.ChannelRepository;
import com.cjteam.xiao.repository.IntegralRepository;
import com.cjteam.xiao.repository.UserRepository;
import com.cjteam.xiao.service.ChannelService;
import com.cjteam.xiao.service.IOSMessageProvider;
import com.cjteam.xiao.service.IntegralService;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.service.impl.BasicServiceImpl;
import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.util.WhereImpl;
import com.cjteam.xiao.xiaohua.model.XiaohuaContent;
import com.cjteam.xiao.xiaohua.repository.XiaohuaContentRepository;
import com.cjteam.xiao.xiaohua.service.XiaohuaContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenlong on 2014/7/23.
 */
@Service
public class XiaohuaContentServiceImpl extends BasicServiceImpl<XiaohuaContent> implements XiaohuaContentService {

    @Resource
    private IntegralRepository integralRepository ;
    @Resource
    private IntegralService integralService ;
    @Resource
    private UserService userService ;
    @Resource
    public UserRepository userRepository  ;
    @Resource
    public ChannelRepository channelRepository ;
    @Autowired
    private IOSMessageProvider iosMessageProvider ;
    private static String DIANZAN   = "DIANZAN"  ;
    private static String appId  = "xiaohua";
    @Autowired
    private XiaohuaContentRepository xiaohuaContentRepository ;
	@Autowired
	XiaohuaContentServiceImpl(XiaohuaContentDao xiaohuaContentDao) {
		super(xiaohuaContentDao);
	}
	@Transactional(readOnly = true)
    public XiaohuaContent getByTitle(String title){
        return     xiaohuaContentRepository.findByTitle(title) ;
    }
    @Transactional(readOnly = true)
    public XiaohuaContent getByTargetIdAndXiaohuaSpiderConfigId(String targetId ,int id ){
    	return  xiaohuaContentRepository.findByTargetIdAndXiaohuaSpiderConfigId(targetId ,  id) ; 
    }
    public void save(XiaohuaContent xiaohuaContent){
        xiaohuaContent.setCreateTime(new Date());
        this.xiaohuaContentRepository.save(xiaohuaContent) ;
    }
    @Transactional(readOnly = true)
    public Page<XiaohuaContent> getByType(String type ,  PageBasicInfo pageBasicInfo){
        Pageable pageable =  new PageRequest(pageBasicInfo.getPage(), pageBasicInfo.getSize() , Sort.Direction.DESC ,XiaohuaContent.Field.id.toString() );
        String newType ;
        if (XiaohuaContent.Type.image.toString().equals(type)){
            newType = XiaohuaContent.Type.image.toString() ;
        }
        else{
            newType = XiaohuaContent.Type.text.toString() ;
        }
        return  this.xiaohuaContentRepository.findByType(newType , pageable) ;
    }

    @Transactional
     public void clickGood(int id  , String userId){
        this.xiaohuaContentRepository.updateGood(id);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        Date upTime = null;
        try {
            upTime =  formatter.parse(dateString);
        } catch (ParseException e) {        }
        int accessNumber =  integralRepository.countByUserIdAndChannelCodeAndCreateTime(userId,this.DIANZAN ,upTime ) ;
        if (accessNumber<10){
            User user = this.userService.getOne(this.appId , userId) ;
            if (user!=null){
                Integral integral = new Integral() ;
                integral.setUser(user);
                Channel channel =channelRepository.findByCode(this.DIANZAN) ;
                integral.setChannel(channel);
                integral.setScore(1);
                integral.setStatus(true);
                integral.setAppId(this.appId);
                userRepository.awardScore(user.getId(),integral.getScore());
                this.integralService.save(integral);
                this.iosMessageProvider.push(this.appId,"点赞获得1个乐豆",user.getToken());
            }
        }
    }
    @Transactional
    public void clickBad(int id){
        this.xiaohuaContentRepository.updateBad(id);
    }
    @Transactional(readOnly = true)
    public Page<XiaohuaContent> getByTitle(String title , PageBasicInfo pageBasicInfo){
    	WhereImpl where = new WhereImpl() ;
    	if(!"".equals(title) && title!=null){
    		where.like("title", title) ;
    	}
    	where.sort(WhereImpl.SortDirection.DESC , XiaohuaContent.Field.sort.toString() , XiaohuaContent.Field.id.toString()) ; 
    	return  this.getPage(pageBasicInfo, where) ; 
    }
}
