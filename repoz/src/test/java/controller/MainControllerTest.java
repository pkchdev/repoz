package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import repoz.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MainControllerTest {

	@Autowired
	private MockMvc mockMvc;


	
	
    
	@Test
	public void getSlash() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrlPattern("*://**/login"));
	}

	@Test
	public void getIndexOffline() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/index").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrlPattern("*://**/login"));
	}
	
	@Test
	public void getIndexOnline() throws Exception {
		
		MediaType contentTypeForm = new MediaType(MediaType.APPLICATION_FORM_URLENCODED.getType(), 
				MediaType.APPLICATION_FORM_URLENCODED.getSubtype(),Charset.forName("utf-8"));
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").with(csrf())
				.session(new MockHttpSession())
				.contentType(contentTypeForm)
				.param("username", "pkch")
				.param("password", "pkch")
			)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/"))
			.andExpect(authenticated().withUsername("pkch"))
			.andExpect(authenticated().withRoles("ADMIN"))
			.andReturn();
		
		//return (MockHttpSession) result.getRequest().getSession();
	}

	

	@Test
	public void getLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	// registry.addViewController("/").setViewName("index");
	// registry.addViewController("/index").setViewName("index");
	// registry.addViewController("/login").setViewName("login");
	////
	// @Test
	// public void getNotFound() throws Exception {
	// mvc.perform(MockMvcRequestBuilders.get("/" +
	// UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
	// .andExpect(status().isNotFound());
	// }

}
