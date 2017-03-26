package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.Helps;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by ChenLong on 14-3-4.
 */
public class HelpsVo extends ResponseVo {
    List<HelpVo> helpVos;

    public List<HelpVo> getHelpVos() {
        return helpVos;
    }

    public void setHelpVos(List<HelpVo> helpVos) {
        this.helpVos = helpVos;
    }

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(helpVos) ? 0 : helpVos.size();
    }

    public void append(HelpVo helpVo) {
        if (null != helpVos)
            helpVos.add(helpVo);
    }
}
