package com.udacity.eurekaserver;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;




import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PricingServiceApplicationTests {

	 @Autowired
	    private MockMvc mvc;

	
	@Test
	public void contextLoads() {
	}
	
	 @Test
	    public void findPrice() throws Exception {
	   
	    	
	    	 mvc.perform( MockMvcRequestBuilders
	    		      .get("/prices/{id}", 1)
	    		      .accept(MediaType.APPLICATION_JSON))
	    		      .andDo(print())
	    		      .andExpect(status().isOk());
	    		     
	    		
	    }

}
