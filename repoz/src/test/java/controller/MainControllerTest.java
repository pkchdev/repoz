package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import javax.servlet.Filter;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import repoz.Application;
import repoz.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//@AutoConfigureMockMvc
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainControllerTest extends UtilsTests {

	//@Autowired
	
	private MockMvc mockMvc;
	private MockHttpSession session;

	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build(); 
	}
	 
	 
	@Test
	public void getSlash() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/").contentType(contentType)).andExpect(status().is3xxRedirection())
			.andExpect(unauthenticated())
			.andExpect(redirectedUrlPattern("http*://localhost/login"));
	}

	@Test
	public void getSlashIndex() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(contentType))
			.andExpect(unauthenticated())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrlPattern("http*://localhost/login"))
			.andReturn();
	}

	@Test
	public void performLoginLogout() throws Exception {
		mockMvc.perform(formLogin("/login").user("pkch").password("pkch"))
			.andExpect(authenticated())	
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/"))
			.andReturn();
		
		mockMvc.perform(logout())
			.andExpect(unauthenticated())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/login?logout"))
			.andReturn();
	}

	@Test
	public void performLoginFailed() throws Exception {
		mockMvc.perform(formLogin("/login").password("invalid"))
			.andExpect(unauthenticated())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/login?error"))
			.andReturn();
	}
	
	@Test
	public void getLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login").contentType(contentType))
			.andExpect(unauthenticated())
			.andExpect(status().isOk());
	}
	
	
	
	@Test
	public void registrationUser() throws Exception {
		// session = performLogin(mockMvc, "pkch", "pkch");
		
		User user = new User();
		user.setUsername("toto");
		user.setPassword("test");
		user.setPasswordConfirm("test");
		
		System.out.println(toJson(user));
		mockMvc.perform(MockMvcRequestBuilders.post("/registration")
				.contentType(contentType)
				//.session(session)
				.with(csrf())
				.content(toJson(user)))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"))
		.andReturn();
		
		//logout(mockMvc);
	}

}
