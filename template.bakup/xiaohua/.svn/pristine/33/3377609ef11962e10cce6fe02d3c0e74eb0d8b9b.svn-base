package com.cjteam.xiao.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.cjteam.xiao.util.Where;

public interface Base1Dao<T> {
	public List<T> getList(Where where , int limit , int limit1) ;
	public Long  getCount(Where where) ;
	public PageImpl<T> getPage(Pageable pageable , Where where ); 
	public void seve(T t);
	public void update(T t) ;
	public void update(Map<String, String> data , Where where  ) ;
    public T get(int id) ;
}
