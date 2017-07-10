package repoz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import repoz.model.Car;
import repoz.service.CarService;

@RequestMapping("/cars")
@Controller
public class CarController {

	@Autowired
	private CarService carService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		List<Car> cars = carService.readAll();
		model.addAttribute("cars", cars);
		return "/cars";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String get(@ModelAttribute Car car, BindingResult bindingResult, Model model) {
		// Validate
		carService.create(car);
		return "/cars";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(Model model, Long id) {
		Car car = carService.read(id);
		model.addAttribute("car", car);
		return "/cars";
	}
}
