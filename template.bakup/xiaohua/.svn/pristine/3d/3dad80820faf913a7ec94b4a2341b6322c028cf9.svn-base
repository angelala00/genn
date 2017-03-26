package com.cjteam.xiao.web.vo;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong on 14-3-2.
 */
public class StringListResponseVo extends ResponseVo {
    private List<String> contents;

    public StringListResponseVo() {
        contents = new ArrayList<String>();
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(contents) ? 0 : contents.size();
    }

    public void append(String content) {
        contents.add(content);
    }
}
