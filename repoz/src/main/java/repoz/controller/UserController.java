package repoz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import repoz.model.User;
import repoz.service.UserService;

@RequestMapping("/users")
@Controller
public class UserController {

	@Autowired
	private UserService userService;


	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		List<User> users = userService.readAll();
		model.addAttribute("users", users);
		return "index";
	}
	


}
