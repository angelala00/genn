package cltest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cjteam.xiao.dao.DailyRegionIntegralLimitDao;
import com.cjteam.xiao.model.DailyRegionIntegralLimit;
import com.cjteam.xiao.web.controller.admin.AdministratorController;
import com.cjteam.xiao.web.controller.admin.DailyRegionIntegralLimitController;

public class DailyRegionIntegralLimitControllerTest extends TestBasis  {
	@Resource
	private DailyRegionIntegralLimitController dailyRegionIntegralLimitController;
	@Before  
    public void setup() {  
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.dailyRegionIntegralLimitController).build();  
    }
	@Test
	public void test (){
		ResultActions ra = null;
		try {
			ra = this.mockMvc.perform(MockMvcRequestBuilders
				.get("/admin/dailyRegionIntegralLimit").param("cityName", "d")
		        .accept(MediaType.APPLICATION_JSON)
			);
			MvcResult mr = ra.andReturn();  
			String result = mr.getResponse().getContentAsString();
			System.out.println(result);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
