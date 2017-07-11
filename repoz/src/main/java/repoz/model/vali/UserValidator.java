package repoz.model.vali;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import repoz.model.User;
import repoz.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user = (User) target;

		// Username validation
		if (user.getUsername().length() < 6 || user.getUsername().length() > 50) {
			errors.rejectValue("username", "user.username.size");
		}

		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "user.username.duplicate");
		}

		// Password validation
		if (user.getPassword().length() < 6 || user.getPassword().length() > 100) {
			errors.rejectValue("password", "user.password.size");
		}

		// Password confirm validation
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "user.passwordConfirm.difference");
		}
	}
}
