package com.cjteam.xiao.service.doublenine.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by ChenLong
 * Date: 13-10-22
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DoubleNineProduct {
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "price")
    private Integer price;
    @XmlAttribute(name = "info")
    private String info;

    public DoubleNineProduct() {
    }

    public DoubleNineProduct(String id, String name, Integer price, String info) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("id:").append("\"").append(getId()).append("\"").append(",");
        sb.append("name:").append("\"").append(getName()).append("\"").append(",");
        sb.append("price:").append("\"").append(getPrice()).append("\"").append(",");
        sb.append("info:").append("\"").append(getInfo()).append("\"");
        sb.append("}");
        return sb.toString();
    }
}
