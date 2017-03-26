package cltest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import redis.clients.jedis.Jedis;


public class RedisTest extends TestBasis {
	@Autowired  
    private StringRedisTemplate  stringRedisTemplate; 
	@Test
	public void test(){
		  this.stringRedisTemplate.boundValueOps("dd").set("ddd");;  
		try {  
		} catch (Exception e) {  
		} 
		final String strkey = "dd";  
		String a =  stringRedisTemplate.execute(
			new RedisCallback<String>() {  
				public String doInRedis(RedisConnection connection) throws DataAccessException {  
					byte[] bkey = stringRedisTemplate.getStringSerializer().serialize("dd");  
					if (connection.exists(bkey)) {  
						List<byte[]> value = connection.hMGet(bkey);  
						return stringRedisTemplate.getStringSerializer().deserialize(value.get(0)) ; 
					}  
					return null;  
				}  
			}
		);
		System.out.println(a);
	}
}
