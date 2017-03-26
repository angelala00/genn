package com.cjteam.xiao.web.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong on 2014/4/28.
 */
public class ScoreResponseV2 extends ResponseVo {
    private Long score = 0l;
    private Long totally = 0l;
    private List<Object> external = new ArrayList<Object>(2);

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public List<Object> getExternal() {
        return external;
    }

    public void setExternal(List<Object> external) {
        this.external = external;
    }

    public void append(Object info) {
        external.add(info);
    }

    public Long getTotally() {
        return totally;
    }

    public void setTotally(Long totally) {
        this.totally = totally;
    }
}
