package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.Helps;

/**
 * Created by ChenLong on 14-3-4.
 */
public class HelpVo {
    private Integer id=0;
    private String title="";
    private String content="";

    public HelpVo(Helps helps) {
        if (null == helps)
            return;
        setId(helps.getId());
        setTitle(helps.getTitle());
        setContent(helps.getContent());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
