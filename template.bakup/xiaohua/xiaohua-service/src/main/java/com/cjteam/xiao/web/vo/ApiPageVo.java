package com.cjteam.xiao.web.vo;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by chenlong on 2014/7/16.
 */
public class ApiPageVo<T> {
    private List<T> content ;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    private  int number ;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public void setPage(Page page){
        this.number = page.getNumber() ;
        this.totalPages = page.getTotalPages() ;
    }
    private int totalPages ;
}
