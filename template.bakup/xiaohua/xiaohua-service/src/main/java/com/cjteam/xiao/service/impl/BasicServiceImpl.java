package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.dao.Base1Dao;
import com.cjteam.xiao.service.BasicService;
import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.util.Where;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public class BasicServiceImpl<T> implements BasicService<T> { 
	private  Base1Dao<T> dao ;
	public BasicServiceImpl(Base1Dao<T> dao){
		 this.dao = dao ;
	}
	public PageImpl<T> getPage(PageBasicInfo pageBasicInfo){
		return this.getPage(pageBasicInfo, null) ;
	}
	@Override
	public PageImpl<T> getPage(PageBasicInfo pageBasicInfo, Where where) {
		Pageable pageable =  new PageRequest(pageBasicInfo.getPage(), pageBasicInfo.getSize());
		return this.dao.getPage(pageable, where);
	}
	@Override
	public void update(Map<String, String> data, Where where) {
		 this.dao.update(data, where);
	}
	@Override
	public void update(T t) {
		 this.dao.update(t);
	}
	@Override
	public void seve(T t) {
		this.dao.seve(t);
	}

    @Override
    public T get(int id) {
        return this.dao.get(id);
    }

    public List<T> getList(Where where , int limit , int limit1){
        return this.dao.getList(where , limit  , limit1) ;
    }
    public List<T> getList(){
        return this.getList(null ,0) ;
    }
    public List<T> getList(Where where , int limit){
        return this.getList(where ,0 ,limit) ;
    }
}