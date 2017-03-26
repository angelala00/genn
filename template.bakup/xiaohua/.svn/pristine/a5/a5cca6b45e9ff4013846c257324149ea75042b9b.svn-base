package cltest;

import static org.junit.Assert.*;  

import java.util.List;  
  



import javax.annotation.Resource;

import org.junit.Before;  
import org.junit.After;  
import org.junit.Test;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
  
/** 
 *  
 * @author snowolf 
 * @version 1.0 
 * @since 1.0 
 */  
public class ListOpsTest extends TestBasis {  
    @Resource
    private ListOps listOps;  
    private String key = "queue";  
 
    @Before  
    public void before() throws Exception {  
    	
        System.out.println("------------IN---------------");  
        for (int i = 0; i < 5; i++) {  
            String uid = "u" + i;  
            System.out.println(uid);  
            listOps.in(key, uid);  
        }  
    }  
  
  //  @Test  
    public void after() {  
        // ------------OUT---------------  
        System.out.println("------------OUT---------------");  
        long length = listOps.length(key);  
        for (long i = 0; i < length; i++) {  
            String uid = listOps.out(key);  
            System.out.println(uid);  
        }  
    }  
  
   // @Test  
    public void stack() {  
        // ------------PUSH---------------  
        String key = "stack";  
        int len = 5;  
        System.out.println("------------PUSH---------------");  
        for (int i = 0; i < len; i++) {  
            String uid = "u" + System.currentTimeMillis();  
            System.out.println(uid);  
            listOps.push(key, uid);  
        }  
  
        long length = listOps.length(key);  
        assertEquals(len, length);  
  
        // ------------POP---------------  
        System.out.println("------------POP---------------");  
        for (long i = 0; i < length; i++) {  
            String uid = listOps.pop(key);  
            System.out.println(uid);  
        }  
    }  
  
   // @Test  
    public void index() {  
  
        // -------------INDEX-------------  
        String value = listOps.index(key, 3);  
        assertEquals("u3", value);  
    }  
  
   // @Test  
    public void range() {  
        // -------------RANGE-------------  
        List<String> list = listOps.range(key, 3, 5);  
        boolean result1 = list.contains("u3");  
        assertEquals(true, result1);  
  
        boolean result2 = list.contains("u1");  
        assertEquals(false, result2);  
    }  
  
   // @Test  
    public void trim() {  
        // ------------TRIM---------------  
        List<String> list = listOps.range(key, 3, 5);  
        listOps.trim(key, 3, 5);  
        boolean result3 = list.contains("u1");  
        assertEquals(false, result3);  
    }  
  
    @Test  
    public void set() {  
        // ------------SET-----------------  
        List<String> list = listOps.range(key, 0, 3);  
      try {
    	  System.out.println("++++"+list.get(4));
		
	} catch (Exception e) {
		e.printStackTrace();
	}
        
  
    }  
  
    //@Test  
    public void remove() {  
        // ------------REMOVE-----------------  
        listOps.remove(key, 4, "u4");  
        String value = listOps.index(key, 4);  
        assertEquals(null, value);  
  
    }  
}  