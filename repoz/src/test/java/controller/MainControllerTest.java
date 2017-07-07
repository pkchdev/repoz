package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import repoz.Application;
import repoz.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
public class MainControllerTest {

	private MockMvc mockMvc; 
	
	@Autowired
	WebApplicationContext webContext;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webContext).addFilter(springSecurityFilterChain).build(); 
	}
	 
	@Test
	public void getSlash() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/").contentType(UtilsTest.contentTypeForm)).andExpect(status().is3xxRedirection())
			.andExpect(unauthenticated())
			.andExpect(redirectedUrlPattern("http*://localhost/login"));
	}

	@Test
	public void getSlashIndex() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(UtilsTest.contentTypeForm))
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
		mockMvc.perform(MockMvcRequestBuilders.get("/login").contentType(UtilsTest.contentTypeForm))
			.andExpect(unauthenticated())
			.andExpect(status().isOk());
	}
	
	@Test
	public void registrationUser() throws Exception {
		User user = new User();
		String username = UtilsTest.createStringWithLength(10, true, true, false);
		String password = UtilsTest.createStringWithLength(20, true, true, true);
		user.setUsername(username);
		user.setPassword(password);
		user.setPasswordConfirm(password);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/registration")
				.contentType(UtilsTest.contentTypeForm)
				//.session(session)
				.with(csrf())
				.content(UtilsTest.toJson(user)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/index"))
		.andReturn();
	}
	
	@Test
	public void memo() {
//		private MockHttpSession session;
//		mockMvc.perform(MockMvcRequestBuilders.post("/registration")
//				.contentType(UtilsTest.contentType)
//				//.session(session)
//				.with(csrf())
//				.content(UtilsTest.toJson(user)))
//		.andExpect(status().is3xxRedirection())
//		.andExpect(redirectedUrl("/index"))
//		.andReturn();
//		session = performLogin(mockMvc, "pkch", "pkch");
//		logout(mockMvc);
	}

}
