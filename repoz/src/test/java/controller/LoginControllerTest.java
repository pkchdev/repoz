package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import misc.AbstractTest;
import repoz.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
public class LoginControllerTest extends AbstractTest {

	 
	@Test
	public void getSlash() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/").contentType(contentTypeFormUrlEncoded)).andExpect(status().is3xxRedirection())
			.andExpect(unauthenticated())
			.andExpect(redirectedUrlPattern("http*://localhost/login"));
	}

	@Test
	public void getSlashIndex() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(contentTypeFormUrlEncoded))
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
		mockMvc.perform(MockMvcRequestBuilders.get("/login").contentType(contentTypeFormUrlEncoded))
			.andExpect(unauthenticated())
			.andExpect(status().isOk());
	}
}
