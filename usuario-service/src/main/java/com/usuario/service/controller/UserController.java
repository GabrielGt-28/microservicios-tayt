	package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entity.User;
import com.usuario.service.model.Car;
import com.usuario.service.model.Moto;
import com.usuario.service.service.UserService;

@RestController
@RequestMapping("/usuario")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> listUsers(){
		List<User> users = userService.getAll();
		
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(users);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") long id){
		User user = userService.getUserById(id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User newUser = userService.save(user);
		
		return ResponseEntity.ok(newUser);
	}
	
	@DeleteMapping("/{id}")
	public boolean removeUser(@PathVariable("id") long id) {
		User user = userService.getUserById(id);
		if(user == null) {
			return false;
		}
		
		userService.remove(id);
		
		return true;
		
	}
	 
	@GetMapping("carros/{userId}")
	public ResponseEntity<List<Car>> getListCars(@PathVariable("userId") long id){
		
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Car> cars = userService.getCars(id);
		return ResponseEntity.ok(cars);
	}
	
	@GetMapping("motos/{userId}")
	public ResponseEntity<List<Moto>> getListMotos(@PathVariable("userId") long id){
		
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Moto> motos = userService.getMotos(id);
		return ResponseEntity.ok(motos);
		
	}
	
	@PostMapping("/carro/{userId}")
	public ResponseEntity<Car> saveCarInUser(@PathVariable("userId") long userId, @RequestBody Car car){
		Car newCar =  userService.saveCar(userId, car);
		return ResponseEntity.ok(newCar);
	}
	
	@PostMapping("/moto/{userId}")
	public ResponseEntity<Moto> saveMoto(@PathVariable("userId") long userId, @RequestBody Moto moto){
		Moto newMoto =  userService.saveMoto(userId, moto);
		return ResponseEntity.ok(newMoto);
	}
	
	@GetMapping("/allVehiclesByUser/{userId}")
	public ResponseEntity<Map<String, Object>> getAllVehiclesByUser(@PathVariable("userId") long userId){
		Map<String, Object> result = userService.getUserAndVehicles(userId);
		return ResponseEntity.ok(result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
