package cltest;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cjteam.xiao.service.impl.UserServiceImpl;
import com.cjteam.xiao.web.controller.UserController;
public class UserControllerTest extends TestBasis {
    @Resource
    private UserController userController;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }
    @Test
	public void info()  {
        ResultActions ra = null;
        try {
            ra = this.mockMvc.perform(MockMvcRequestBuilders
                            .get("/user/info").param("appId", "xiaohua").param("userId","3ec0274dcaa16c419fd20ca4bf5a6728")
                            .accept(MediaType.APPLICATION_JSON)
            );
            MvcResult mr = ra.andReturn();
            String result = mr.getResponse().getContentAsString();
            System.out.println("++++++++++++++++++++++++++++++"+result+"-----------------");
            System.out.println("*********************info===========");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    @Test
    public void init(){
        ResultActions ra = null;
        try {
            String mac="aaaaa" ;
            String openUdid = "asfdadsf" ;
            String token = "adfadsf" ;
            String userId =  DigestUtils.md5Hex(mac + openUdid + token + UserServiceImpl.privatekey) ;
            ra = this.mockMvc.perform(MockMvcRequestBuilders
                            .get("/user/init")
                            .param("mac", mac)
                            .param("openUdid", openUdid)
                            .param("token", token)
                            .param("telphone", "1212121")
                            .param("appId", "xiaohua")
                            .param("versionNo", "1.14")
                            .param("userId",userId)
                            .accept(MediaType.APPLICATION_JSON)
            );
            MvcResult mr = ra.andReturn();
            String result = mr.getResponse().getContentAsString();
            System.out.println("++++++++++++++++++++++++++++++"+result+"-----------------");
            System.out.println("*********************info===========");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void update(){
        ResultActions ra = null;
        try {
            String mac="aaaaa" ;
            String openUdid = "asfdadsf" ;
            String token = "adfadsf" ;
            String userId =  DigestUtils.md5Hex(mac + openUdid + token + UserServiceImpl.privatekey) ;
            ra = this.mockMvc.perform(MockMvcRequestBuilders
                            .get("/user/update")
                            .param("mac", mac)
                            .param("openUdid", openUdid)
                            .param("token", token)
                            .param("telphone", "1212121")
                            .param("appId", "xiaohua")
                            .param("userId",userId)
                            .param("newpsw","asdfasdf11")
                            .accept(MediaType.APPLICATION_JSON)
            );
            MvcResult mr = ra.andReturn();
            String result = mr.getResponse().getContentAsString();
            System.out.println("++++++++++++++++++++++++++++++"+result+"-----------------");
            System.out.println("*********************info===========");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
