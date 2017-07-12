package repoz.service;

import java.util.List;

import repoz.model.Car;

public interface CarService {

	public void create(Car c);
	public List<Car> readAll();
	public Car read(Long id);
	public void delete(Long id);
}
