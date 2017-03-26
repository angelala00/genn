package com.cjteam.xiao.xiaohua.service.impl;

        import com.cjteam.xiao.xiaohua.model.XiaohuaSpiderConfig;
        import com.cjteam.xiao.xiaohua.model.XiaohuaSpiderException;
        import com.cjteam.xiao.xiaohua.repository.XiaohuaSpiderConfigRepository;
        import com.cjteam.xiao.xiaohua.repository.XiaohuaSpiderExceptionRepository;
        import com.cjteam.xiao.xiaohua.service.XiaohuaSpiderConfigService;
        import com.cjteam.xiao.xiaohua.service.XiaohuaSpiderExceptionService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.Date;

/**
 * Created by chenlong on 2014/7/23.
 */
@Service
public class XiaohuaSpiderExceptionServiceImpl implements XiaohuaSpiderExceptionService {
    @Autowired
    private XiaohuaSpiderExceptionRepository xiaohuaSpiderExceptionRepository ;
    @Transactional
    public void save(XiaohuaSpiderException xiaohuaSpiderException){
        xiaohuaSpiderException.setCreateTime(new Date());
        this.xiaohuaSpiderExceptionRepository.save(xiaohuaSpiderException) ;
    }
}
