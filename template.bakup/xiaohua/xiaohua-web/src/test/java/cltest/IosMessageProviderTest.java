package cltest;

import java.util.List;
import java.util.Set;

import com.cjteam.xiao.service.IOSMessageProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

public class IosMessageProviderTest  extends TestBasis{
	@Autowired  
	private IOSMessageProvider iosMessageProvider ;
	@Test
	public void testa(){
      //  this.iosMessageProvider.push("xiaohua","111","<456e7c46 4170d927 e8548317 d98d471e c5fc1f97 7b16eadb de65045c 2a014316>");
        this.iosMessageProvider.push("xiaohua","222","a043cb82b60d156b87cb66112808ed16dcbbbbd72e1477bec1827bb970670303");
      //  this.iosMessageProvider.push("xiaohua","222","456e7c464170d927e8548317d98d471ec5fc1f977b16eadbde65045c2a014316");
	}
}
