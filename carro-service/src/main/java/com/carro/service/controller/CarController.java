package com.carro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entity.Car;
import com.carro.service.service.CarService;

@RestController
@RequestMapping("/carro")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@GetMapping
	public ResponseEntity<List<Car>> listCars(){
		
		List<Car> cars = carService.getAllCars();
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> getCar(@PathVariable("id") long id){
		
		Car car = carService.getCardById(id);
		if (car == null) {
			return ResponseEntity.noContent().build();
		}	
		return ResponseEntity.ok(car);
	}
	
	
	@PostMapping
	public ResponseEntity<Car> saveCar(@RequestBody Car car){
		Car newCar = carService.save(car);
		return ResponseEntity.ok(newCar); 
	}
	
	
	@GetMapping("/usuario/{userId}")
	public ResponseEntity<List<Car>> listCarsByUserId(@PathVariable("userId") long userId){
		
		List<Car> cars = carService.byUserId(userId);
		
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
