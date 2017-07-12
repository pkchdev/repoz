package repoz.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repoz.model.Car;
import repoz.repository.CarRepository;
import repoz.service.CarService;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carPepository;

	
	@Override
	public void create(Car c) {
		carPepository.create(c);
	}

	@Override
	public List<Car> readAll() {
		return carPepository.readAll(Car.class, Order.asc("date"));
	}

	@Override
	public Car read(Long id) {
		return carPepository.read(Car.class, id);
	}


	@Override
	public void delete(Long id) {
		Car car = new Car();
		car.setId(id);
		carPepository.delete(car);
	}

}
