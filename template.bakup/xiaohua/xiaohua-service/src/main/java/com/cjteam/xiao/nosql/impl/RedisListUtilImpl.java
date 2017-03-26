package com.cjteam.xiao.nosql.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.cjteam.xiao.nosql.NosqlListUtil;;
@Component
public class RedisListUtilImpl  implements NosqlListUtil{
	 @Autowired  
    private StringRedisTemplate stringRedisTemplate;  
    /** 
     * 压栈 
     */  
    public Long push(String key, String value) {  
        return this.stringRedisTemplate.opsForList().leftPush(key, value);  
    }  
    /** 
     * 出栈 
     */  
    public String pop(String key) {  
        return this.stringRedisTemplate.opsForList().leftPop(key);  
    }  
    /** 
     * 入队 
     */  
    public Long in(String key, String value) {  
        return this.stringRedisTemplate.opsForList().rightPush(key, value);  
    }  
    /** 
     * 出队 
     */  
    public String out(String key) {  
        return this.stringRedisTemplate.opsForList().leftPop(key);  
    }  
    /** 
     * 栈/队列长 
     */  
    public Long length(String key) {  
        return this.stringRedisTemplate.opsForList().size(key);  
    }  
  
    /** 
     * 范围检索 
     */  
    public List<String> range(String key, int start, int end) {  
        return this.stringRedisTemplate.opsForList().range(key, start, end);  
    }  
  
    /** 
     * 移除 
     */  
    public void remove(String key, long i, String value) {  
    	this.stringRedisTemplate.opsForList().remove(key, i, value);  
    }  
  
    /** 
     * 检索 
     */  
    public String index(String key, long index) {  
        return this.stringRedisTemplate.opsForList().index(key, index);  
    }  
  
    /** 
     * 置值 
     */  
    public void set(String key, long index, String value) {  
    	this.stringRedisTemplate.opsForList().set(key, index, value);
    }
    /**
     * 裁剪 
     */  
    public void trim(String key, long start, int end) {  
    	this.stringRedisTemplate.opsForList().trim(key, start, end);  
    }
    /**
     * 获得list
     */
	@Override
	public List<String> getList(String key) {
		long length = this.length(key);
		return  this.range(key, 0, (int) (length-1));
	}
}
