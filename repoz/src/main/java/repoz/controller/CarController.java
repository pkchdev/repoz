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
import repoz.model.vali.CarValidator;
import repoz.service.CarService;

@RequestMapping("/cars")
@Controller
public class CarController {

	@Autowired
	private CarService carService;

	@Autowired
	private CarValidator carValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		List<Car> cars = carService.readAll();
		model.addAttribute("cars", cars);
		model.addAttribute("car", new Car());
		return "cars";
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String get(@ModelAttribute Car car, BindingResult bindingResult, Model model, String error) {
		carValidator.validate(car, bindingResult);
		if (bindingResult.hasErrors()) {
			return "cars";
		}
		carService.create(car);
		return "cars";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(Model model, Long id) {
		Car car = carService.read(id);
		model.addAttribute("car", car);
		return "/cars";
	}
}
