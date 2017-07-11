package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import misc.AbstractTest;
import misc.UtilsTest;
import repoz.model.User;

public class RegisterControllerTest extends AbstractTest {

	@Test
	public void registrationUser() throws Exception {
		User user = new User();
		String username = UtilsTest.createRandomString(10, true, true, false);
		String password = UtilsTest.createRandomString(20, true, true, true);
		user.setUsername(username);
		user.setPassword(password);
		user.setPasswordConfirm(password);

		mockMvc.perform(MockMvcRequestBuilders.post("/registration")
			.contentType(contentTypeFormUrlEncoded)
			.with(csrf())
			.param("username", user.getUsername())
			.param("password", user.getPassword())
			.param("passwordConfirm", user.getPasswordConfirm()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/index"))
			.andReturn();
	}

	@Test
	public void memo() {
		// private MockHttpSession session;
		// mockMvc.perform(MockMvcRequestBuilders.post("/registration")
		// .contentType(UtilsTest.contentType)
		// //.session(session)
		// .with(csrf())
		// .content(UtilsTest.toJson(user)))
		// .andExpect(status().is3xxRedirection())
		// .andExpect(redirectedUrl("/index"))
		// .andReturn();
		// session = performLogin(mockMvc, "pkch", "pkch");
		// logout(mockMvc);
	}

}
