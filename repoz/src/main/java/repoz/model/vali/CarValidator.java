package repoz.model.vali;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import repoz.model.Car;

@Component
public class CarValidator implements Validator {


	@Override
	public boolean supports(Class<?> aClass) {
		return Car.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Car car = (Car) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maker", "car.maker.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "car.model.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "car.date.empty");
		
		if(car.getDate() == null) {
			errors.rejectValue("date", "car.date.empty");
		}
		
		if (car.getMaker().length() > 50) {
			errors.rejectValue("maker", "car.maker.size");
		}

		if (car.getModel().length() > 100) {
			errors.rejectValue("model", "car.model.size");
		}
		
		if(car.getDate().isAfter(LocalDate.now())) {
			errors.rejectValue("date", "car.date.future");
		}
	}
}
