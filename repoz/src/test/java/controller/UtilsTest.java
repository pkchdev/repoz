package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UtilsTest {

	public static final MediaType contentTypeForm = new MediaType(MediaType.APPLICATION_FORM_URLENCODED.getType(),
			MediaType.APPLICATION_FORM_URLENCODED.getSubtype(), Charset.forName("utf-8"));

	public static MockHttpSession performLogin(MockMvc mockMvc, String username, String password) throws Exception {
		ResultActions session = mockMvc.perform(formLogin().user(username).password(password));
		return (MockHttpSession) session.andReturn().getRequest().getSession();
	}

	public static void performLogout(MockMvc mockMvc) throws Exception {
		mockMvc.perform(logout()).andReturn();
	}
	
	public static String toJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsString(obj);
	}
	 
	public static String createStringWithLength(int length, boolean withMaj, boolean withMin, boolean withNum) {
		StringBuilder text = new StringBuilder(length);
		String min = "abcdefghijklmnopqrstuvwxyz";
		String maj = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String num = "0123456789";
		String chars = "";
		
		if(withMaj || withMin || withNum ) {
			if(withMaj) chars += maj; 
			if(withMin) chars += min;
			if(withNum) chars += num;
		
			for(int i = 0; i < length; i++) {
				text.append(chars.charAt(new Random().nextInt(chars.length())));
			}
		}
		
		return text.toString();
	}
}
