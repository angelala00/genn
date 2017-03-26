import cltest.TestBasis;
import com.cjteam.xiao.xiaohua.controller.XiaohuaContentController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

public class XiaohuaContentControllerTest extends TestBasis {
    @Resource
    private XiaohuaContentController xiaohuaContentController;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.xiaohuaContentController).build();
    }
    @Test
	public void list()  {
        ResultActions ra = null;
        try {
            ra = this.mockMvc.perform(MockMvcRequestBuilders
                            .get("/xiaohuaContent/list")
                            .param("type", "image")
                            .accept(MediaType.APPLICATION_JSON)
            );
            MvcResult mr = ra.andReturn();
            String result = mr.getResponse().getContentAsString();
            System.out.println("++++++++++++++++++++++++++++++"+result+"-----------------");
            System.out.println("*********************list===========");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    @Test
    public void clickGood()  {
        ResultActions ra = null;
        try {
            ra = this.mockMvc.perform(MockMvcRequestBuilders
                            .get("/xiaohuaContent/clickGood")
                            .param("id", "1673")
                            .accept(MediaType.APPLICATION_JSON)
            );
            MvcResult mr = ra.andReturn();
            String result = mr.getResponse().getContentAsString();
            System.out.println("++++++++++++++++++++++++++++++"+result+"-----------------");
            System.out.println("*********************list===========");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
