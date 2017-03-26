package cltest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjteam.xiao.util.HttpTools;

public class Testb extends  TestBasis  {
    @Autowired
    private  HttpTools httpTools ;
	@Test
	public void a (){
        String picUrl ="http://i2.xiaohua.fd.zol-img.com.cn/t_s600x5000/g4/M07/0D/09/Cg-4WlQASBKIP9rjAB7lRYzS52gAAQ9bAAAvAEAHuVd502.gif";
        String[] tmp  = picUrl.split("\\.");
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
        httpTools.download(picUrl  ,System.currentTimeMillis()+"." +tmp[tmp.length-1] , "D:\\java\\xiaohua\\webapp-new\\src\\main\\webapp\\resources\\xiaohuaContent\\image\\b"+formatter.format(currentTime) );
    }

}
