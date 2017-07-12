package repoz.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import repoz.model.Car;
import repoz.model.vali.CarValidator;
import repoz.service.CarService;

@RequestMapping("/cars")
@Controller
public class CarController {

	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://temp//";
	
	@Autowired
	private CarService carService;

	@Autowired
	private CarValidator carValidator;
	
	@GetMapping
	public String readAll(Model model) {
		List<Car> cars = carService.readAll();
		model.addAttribute("cars", cars);
		model.addAttribute("car", new Car());
		return "cars";
	}
	
	@PostMapping
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
	
	@GetMapping("/{id}")
	public String read(@PathVariable Long id, Model model) {
		Car car = carService.read(id);
		model.addAttribute("car", car);
		List<Car> cars = carService.readAll();
		model.addAttribute("cars", cars);
		return "cars";
	}

	@PostMapping("/delete")
	public String delete(Model model, Long id) {
		carService.delete(id);
		return "redirect:/cars";
	}



  

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(Model model, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {


        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
           

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

   

}
