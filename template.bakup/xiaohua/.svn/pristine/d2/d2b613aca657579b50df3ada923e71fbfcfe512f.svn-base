package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.About;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong on 2014/7/7.
 */
public class AboutsResponse extends ResponseVo {
    private List<AboutVo> aboutList;

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(aboutList) ? 0 : aboutList.size();
    }

    public List<AboutVo> getAboutList() {
        return aboutList;
    }

    public void setAboutList(List<AboutVo> aboutList) {
        this.aboutList = aboutList;
    }

    public void append(List<About> aboutList) {
        if (CollectionUtils.isEmpty(aboutList))
            return;
        for (About a : aboutList) {
            append(a);
        }

    }

    private void append(About about) {
        if (StringUtils.isNotBlank(about.getContent())) {
            if(aboutList ==null){
                aboutList = new ArrayList<AboutVo>(5);
            }
    aboutList.add(new AboutVo(about));
        }
    }
}