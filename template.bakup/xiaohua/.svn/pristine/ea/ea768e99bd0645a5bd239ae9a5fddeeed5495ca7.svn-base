package com.cjteam.xiao.service;

import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.util.Where;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Map;

public interface  BasicService<T> {
	public PageImpl<T> getPage(PageBasicInfo pageBasicInfo) ;
	public PageImpl<T> getPage(PageBasicInfo pageBasicInfo, Where where) ;
	public void update(Map<String, String> data , Where where  );
	public void update(T t);
	public void seve(T t);
    public T get(int id)  ;
    public List<T> getList(Where where , int limit , int limit1) ;
    public List<T> getList() ;
    public List<T> getList(Where where , int limit) ;
}
