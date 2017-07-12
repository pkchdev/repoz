package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
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
			String maker = UtilsTest.createRandomString(10, true, true, true, true);
			String model = UtilsTest.createRandomString(20, true, true, true, true);
			String description = UtilsTest.createRandomString(500, true, true, true, true);
			LocalDate date = UtilsTest.createRandomLocalDate();
			
			car.setMaker(maker);
			car.setModel(model);
			car.setDate(date);
			car.setDescription(description);
			
			mockMvc.perform(MockMvcRequestBuilders.post("/cars")
				.contentType(contentTypeFormUrlEncoded)
				.session(session)
				.with(csrf())
				.param("maker", car.getMaker())
				.param("model", car.getModel())
				.param("description", car.getDescription())
				.param("date", car.getDate().toString()))
				.andExpect(redirectedUrl("/cars"))
				.andReturn();
		
		} finally {
			performLogout(mockMvc);
		}
	}
	
	@Test
	public void deleteCar() throws Exception {
		
		session = performLogin(mockMvc, "pkch", "pkch");
		
		try {
			Car car = new Car();
			String maker = UtilsTest.createRandomString(10, true, true, true, true);
			String model = UtilsTest.createRandomString(20, true, true, true, true);
			String description = UtilsTest.createRandomString(500, true, true, true, true);
			LocalDate date = UtilsTest.createRandomLocalDate();
			
			car.setMaker(maker);
			car.setModel(model);
			car.setDate(date);
			car.setDescription(description);
			
			// Create
			mockMvc.perform(MockMvcRequestBuilders.post("/cars")
				.contentType(contentTypeFormUrlEncoded)
				.session(session)
				.with(csrf())
				.param("maker", car.getMaker())
				.param("model", car.getModel())
				.param("description", car.getDescription())
				.param("date", car.getDate().toString()))
				.andExpect(redirectedUrl("/cars"))
				.andReturn();
			
			// Read all
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars")
				.contentType(contentTypeFormUrlEncoded)
				.session(session)
				.with(csrf()))
				.andExpect(status().isOk())
				.andReturn();
			
			@SuppressWarnings("unchecked")
			List<Car> cars = (List<Car>) result.getModelAndView().getModel().get("cars");
			Long id = cars.get(0).getId();
			
			mockMvc.perform(MockMvcRequestBuilders.post("/cars/delete")
					.contentType(contentTypeFormUrlEncoded)
					.session(session)
					.with(csrf())
					.param("id", id.toString()))
					.andExpect(status().is(302))
					.andReturn();
	
		
		} finally {
			performLogout(mockMvc);
		}
	}

	
	@Test
	public void readCar() throws Exception {
		
		session = performLogin(mockMvc, "pkch", "pkch");
		int id = 1;
		try {
			
			mockMvc.perform(MockMvcRequestBuilders.get("/cars/" + id)
				.contentType(contentTypeFormUrlEncoded)
				.session(session)
				.with(csrf()))
				.andExpect(status().isOk())
				.andReturn();
		} finally {
			performLogout(mockMvc);
		}
	}
	
	@Test
	public void readAllCars() throws Exception {
		session = performLogin(mockMvc, "pkch", "pkch");
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/cars")
				.contentType(contentTypeFormUrlEncoded)
				.session(session)
				.with(csrf()))
				.andExpect(status().isOk())
				.andReturn();
		} finally {
			performLogout(mockMvc);
		}
	}


}
