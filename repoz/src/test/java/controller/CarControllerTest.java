package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import misc.AbstractTest;
import misc.UtilsTest;
import repoz.model.Car;

public class CarControllerTest extends AbstractTest {

	private MockHttpSession session;
	
	
	@Test
	public void createCar() throws Exception {
		
		session = performLogin(mockMvc, "pkch", "pkch");
		
		try {
			Car car = new Car();
			String maker = UtilsTest.createRandomString(10, true, true, true);
			String model = UtilsTest.createRandomString(20, true, true, true);
			String description = UtilsTest.createRandomString(500, true, true, true);
			LocalDate date = UtilsTest.createRandomLocalDate();
			
			car.setMaker(maker);
			car.setModel(model);
			car.setDate(date);
			car.setDescription(description);
			//car.setPicture(new Byte[] {1,2,3,4,5});
			
			mockMvc.perform(MockMvcRequestBuilders.post("/cars")
				.contentType(contentTypeFormUrlEncoded)
				.session(session)
				.with(csrf())
				.param("maker", car.getMaker())
				.param("model", car.getModel())
				.param("model", car.getDescription())
				.param("date", car.getDate().toString()))
				//.param("picture", car.getPicture().toString()))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		
		} finally {
			performLogout(mockMvc);
		}
	}

	
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
