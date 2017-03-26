package com.cjteam.xiao.nosql;

import java.util.List;

/**
 * 操作Nosql  list工具
 */
public interface NosqlListUtil {
	public Long push(String key, String value) ; 
	public String pop(String key)  ;
	public Long in(String key, String value) ; 
	public String out(String key) ;
	public Long length(String key) ; 
	public List<String> range(String key, int start, int end)  ;  
	public void remove(String key, long i, String value) ; 
	public String index(String key, long index) ; 
	public void set(String key, long index, String value)  ; 
	public void trim(String key, long start, int end) ; 
	public List<String> getList(String key) ;
}
