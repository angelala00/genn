package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.DnProduct;

/**
 * Created by ChenLong
 * Date: 13-10-27
 */
public class DNProductVo {
    public DNProductVo() {
    }

    private Integer id;
    private String dnid="";
    private String name="";
    private Integer price=0;
    private String info="";
    private Integer type=0;

    public DNProductVo(DnProduct product) {
        setId(product.getId());
        setDnid(product.getDnId());
        setName(product.getName());
        setPrice(product.getPrice());
        setInfo(product.getInfo());
        setType(product.getType());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDnid() {
        return dnid;
    }

    public void setDnid(String dnid) {
        this.dnid = dnid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
