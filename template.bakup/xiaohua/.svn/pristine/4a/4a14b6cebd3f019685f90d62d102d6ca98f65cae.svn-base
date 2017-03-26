package com.cjteam.xiao.service.doublenine.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by ChenLong
 * Date: 13-9-18
 */
public class BooleanIntAdapter extends XmlAdapter<Integer, Boolean> {
    @Override
    public Boolean unmarshal(Integer v) throws Exception {
        return v != null && v.intValue() > 0;
    }

    @Override
    public Integer marshal(Boolean v) throws Exception {
        return v ? 1 : 0;
    }
}
