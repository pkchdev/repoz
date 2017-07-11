package controller;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import misc.AbstractTest;

public class UserControllerTest extends AbstractTest {

	
	private MockHttpSession session;
	 
	@Test
	public void getUsers() throws Exception {
		
		session = performLogin(mockMvc, "pkch", "pkch");
	
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.session(session)
				.contentType(contentTypeFormUrlEncoded))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(authenticated())
					.andReturn();
			
		} finally {
			performLogout(mockMvc);
		}
		
	}

}
