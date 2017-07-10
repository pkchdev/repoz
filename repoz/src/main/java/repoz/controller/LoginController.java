 package repoz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,  String error, String logout) {
		
		// Default Spring failure login URL : /login?error
		if (error != null)
			model.addAttribute("error", "Your username and/or password is invalid.");

		// Default Spring success logout URL : /login?logout
		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}
}
