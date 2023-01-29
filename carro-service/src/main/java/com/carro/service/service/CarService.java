package com.carro.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carro.service.entity.Car;
import com.carro.service.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;
	
	public List<Car> getAllCars(){
		return carRepository.findAll();
	}
	
	public Car getCardById(long id) {
		return carRepository.findById(id).orElse(null);
	}
	
	public Car save(Car car) {
		Car newCar = carRepository.save(car);
		return newCar;
	}
	
	public void remove (long id) {
		carRepository.deleteById(id);
	}
	
	public List<Car> byUserId(long userId){
		return carRepository.findByUserId(userId);
		
	}
	
}
