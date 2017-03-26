package com.cjteam.xiao.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;


/**
 *拼接JPQL  
 */
public class JPQLUtil {
	private String table  ;
	private StringBuilder sql   ;
	private StringBuilder whereStr ;
	public  JPQLUtil(@SuppressWarnings("rawtypes") Class t)	{
		this.table =  t.getSimpleName();
		this.whereStr   = new StringBuilder();
	}
	
	public JPQLUtil selectCount (){
		return this.select(" count(*) ");
	}
	public JPQLUtil select (String column){
		this.sql= new StringBuilder();
		if (column!=null){
			this.sql.append("select ").append(column).append(" ");
		}
		this.sql.append("from ").append(this.table);
		return this ;
	}
	public JPQLUtil select(){
		return this.select(null);
	}
	public String toString(){
		if (this.whereStr==null){
			return this.sql.toString();
		}
		else{
			return this.sql.append(this.whereStr).toString();
		}
	}
	public JPQLUtil setWhere(Where where){
		this.whereStr = where.getWhereStr() ;
		return this  ;
	}
	public JPQLUtil update (Map<String, String> data){
		List<String> valuse = new ArrayList<String>();
		for (String key : data.keySet()) {
			valuse.add(key+"='"+data.get(key)+"'");
		}
		this.sql= new StringBuilder("update  ");
		this.sql.append(this.table).append(" set  ").append(Joiner.on(",").join(valuse)) ;
		return this ;
	}
}