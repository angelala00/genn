package com.cjteam.xiao.web.vo;

import java.text.SimpleDateFormat;

import com.cjteam.xiao.model.Exchange;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by ChenLong Date: 13-9-26
 */
public class ExchangeVo {
    private Integer id=0;
    private String type="";//做什么用的
    private Integer cost=0;//消费多少积分
    private Integer amount=0;//金额
    private String createTime="";//时间
    private String state="";
    private String processingTime="";

    public ExchangeVo(Exchange exchange) {
        super();
        this.cost = exchange.getScoreConsumption();
        this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(exchange.getCreateTime());
        if (null != exchange.getProcessingTime())
            this.processingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(exchange.getProcessingTime());
        setAmount(exchange.getMoneyExchange());
        setState(exchange.getState() != null ? exchange.getState().getMeaning() : "");
        this.type = exchange.getProduct().getProductType().getName();
    }

    public ExchangeVo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
