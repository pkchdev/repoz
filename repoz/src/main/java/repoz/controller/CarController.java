package repoz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String readAll(Model model) {
		List<Car> cars = carService.readAll();
		model.addAttribute("cars", cars);
		model.addAttribute("car", new Car());
		return "cars";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute Car car, BindingResult bindingResult, Model model) {
		carValidator.validate(car, bindingResult);
		if (bindingResult.hasErrors()) {
			List<Car> cars = carService.readAll();
			model.addAttribute("cars", cars);
			return "cars";
		}
		carService.create(car);
		return "redirect:/cars";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String read(@PathVariable Long id, Model model) {
		Car car = carService.read(id);
		model.addAttribute("car", car);
		List<Car> cars = carService.readAll();
		model.addAttribute("cars", cars);
		return "cars";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Model model, Long id) {
		carService.delete(id);
		return "redirect:/cars";
	}
}
