package misc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.FixMethodOrder;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import repoz.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
public abstract class AbstractTest {

	
	protected MockMvc mockMvc; 
	
	@Autowired
	protected WebApplicationContext webContext;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webContext).addFilter(springSecurityFilterChain).build(); 
	}
	
	
	protected final MediaType contentTypeFormUrlEncoded = new MediaType(MediaType.APPLICATION_FORM_URLENCODED.getType(),
			MediaType.APPLICATION_FORM_URLENCODED.getSubtype(), Charset.forName("utf-8"));

	protected MockHttpSession performLogin(MockMvc mockMvc, String username, String password) throws Exception {
		ResultActions session = mockMvc.perform(formLogin().user(username).password(password));
		return (MockHttpSession) session.andReturn().getRequest().getSession();
	}

	protected void performLogout(MockMvc mockMvc) throws Exception {
		mockMvc.perform(logout()).andReturn();
	}
	 
}
