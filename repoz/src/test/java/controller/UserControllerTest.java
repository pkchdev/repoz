package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.servlet.Filter;

import org.hibernate.tool.schema.extract.spi.PrimaryKeyInformation;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import repoz.Application;
import repoz.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mockMvc; 
	private MockHttpSession session;
	
	@Autowired
	WebApplicationContext webContext;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webContext).addFilter(springSecurityFilterChain).build(); 
	}
	 
	@Test
	public void getUsers() throws Exception {
		
	session = (MockHttpSession) mockMvc.perform(formLogin().user("pkch").password("pkch")).andReturn().getRequest().getSession();

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.session(session)
				.contentType(UtilsTest.contentTypeForm))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(authenticated())
					//.andExpect(content().contentType(MediaType.APPLICATION_FORM_URLENCODED))
					.andReturn();
			
		} finally {
			mockMvc.perform(logout());
		}
		
	}

}
