package com.cjteam.xiao.util;

import com.cjteam.xiao.util.WhereImpl.SortDirection;
import com.cjteam.xiao.xiaohua.model.XiaohuaContent.Field;

public class WhereImpl implements Where {
	public static enum SortDirection{
		ASC, DESC;
	}
	private StringBuilder whereStr = new StringBuilder(); 
	public StringBuilder getWhereStr(){
		return this.whereStr.append(sort) ;
	}
	private StringBuilder sort  = new StringBuilder() ; 
	public WhereImpl sort(WhereImpl.SortDirection sortDirection , String... properties){
		String tmpaString =" "; 
		if (properties.length>1){
			String separator = " , " ; 
			for(String tmp : properties){
				if (tmpaString.equals(" ")){
					separator=" " ; 
				}
				tmpaString = tmpaString+separator+tmp+" "+sortDirection.toString() ; 
			}
		}
		else{
			tmpaString=properties[0]+" "+sortDirection.toString()+" " ; 
		}
		this.sort.append(" order by ").append(tmpaString)  ;
		return this;
	}
	public WhereImpl  and (){
		return  this.whereFun("and");
	}
	private WhereImpl whereFun (String where){
		if (this.whereStr.toString().equals("")){
			this.whereStr.append(" where ");
		}
		else{
			this.whereStr.append(" ").append(where).append(" ");
		}
		return this ;
	}
	public WhereImpl  or (){
		return  this.whereFun("or");
	}
	public WhereImpl equals(String k  , String v){
		return this.wheresFun(k, "=", v);
	}
	public WhereImpl wheresFun(String k , String  where , String v ){
		if (this.whereStr.toString().equals("")){ 
			this.whereStr.append(" where ");
		}
		this.whereStr.append(k).append(where).append(" '").append(v).append("'");
		return this ;
	}
	public WhereImpl notEquals(String k  , String v){
		return this.wheresFun(k, "<>", v);
	}
	public WhereImpl greaterThanEqual (String k  , String v){
		return this.wheresFun(k, ">=", v);
	}
    public WhereImpl greaterThan (String k  , String v){
        return this.wheresFun(k, ">", v);
    }
	public WhereImpl lessThanEqual (String k , String v){
		return this.wheresFun(k, "<=", v);
	}
    public WhereImpl lessThan (String k , String v){
        return this.wheresFun(k, "<", v);
    }
	public WhereImpl like(String k , String v){
		StringBuilder vv = new StringBuilder("%").append(v).append("%");
		return this.wheresFun(k, " like ", vv.toString());
	}

}
