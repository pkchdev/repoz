package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import repoz.Application;
import repoz.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MainControllerTest extends UtilsTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getSlash() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/").contentType(contentType)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("http*://localhost/login"));
	}

	@Test
	public void getSlashIndex() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(contentType))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrlPattern("http*://localhost/login"));
	}

	@Test
	public void performLogin() throws Exception {
		login(mockMvc, "pkch", "pkch");
		logout(mockMvc);
	}

	@Test
	public void performFailedLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login").with(csrf())
				.session(new MockHttpSession())
				.contentType(contentTypeForm)
				.param("username", "")
				.param("password", "")
			)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/login?error"))
			.andReturn();
	}
	
	@Test
	public void getLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login").contentType(contentType))
				.andExpect(status().isOk());
	}
	

	
	@Test
	public void registDrationUser() throws Exception {
		//MockHttpSession session = login(mockMvc, "pkch", "pkch");
		
		User user = new User();
		user.setUsername("toto");
		user.setPassword("test");
		user.setPasswordConfirm("test");
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/registration")
				.contentType(contentTypeForm)
				.with(csrf())
				.requestAttr("user", user))
				//.session(session)
				//.content(convertObjectToJsonBytes(user)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"))
		.andReturn();
		
		//logout(mockMvc);
	}

}
