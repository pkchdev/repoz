package repoz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import repoz.model.Person;
import repoz.service.IPersonService;

@Controller
public class MainController {

	
	@Autowired
	private IPersonService service;
	
	
	@RequestMapping("/")
	public String index(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
    
		
		model.addAttribute("name", name);
        
		Person p = new Person();
		p.setName("Pierre");
		p.setCountry("Paris");
        
        service.addPerson(p);
		
		return "index";
	}
	
	
	
}
