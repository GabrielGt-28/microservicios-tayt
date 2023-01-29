package com.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entity.User;
import com.usuario.service.feignClients.CarroFeignClients;
import com.usuario.service.feignClients.MotoFeignClients;
import com.usuario.service.model.Car;
import com.usuario.service.model.Moto;
import com.usuario.service.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarroFeignClients carroFeignClients;

	@Autowired
	private MotoFeignClients motoFeignClients;

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getUserById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User save(User user) {
		User newUser = userRepository.save(user);
		return newUser;

	}

	public void remove(long id) {
		userRepository.deleteById(id);
	}

	// forma con restTemplate

	public List<Car> getCars(long userId) {
		List<Car> cars = restTemplate.getForObject("http://localhost:8082/carro/usuario/" + userId, List.class);
		return cars;

	}

	public List<Moto> getMotos(long userId) {
		List<Moto> motos = restTemplate.getForObject("http://localhost:8083/moto/usuario/" + userId, List.class);
		return motos;

	}

	// Forma con feignClient

	public Car saveCar(long userId, Car car) {
		car.setUserId(userId);
		Car newCar = carroFeignClients.save(car);
		return newCar;
	}

	public Moto saveMoto(long userId, Moto moto) {
		moto.setUserId(userId);
		Moto newMoto = motoFeignClients.saveMoto(moto);
		return newMoto;
	}

	public Map<String, Object> getUserAndVehicles(long userId) {
		Map<String, Object> result = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);

		if (user == null) {
			result.put("Mensaje", "El usuario no existe");
			return result;
		}
		result.put("Usuario", user);

		List<Car> cars = carroFeignClients.getCars(userId);

		if (cars.isEmpty()) {
			result.put("Carros", "EL usuario no tiene carros");
		} else {
			result.put("Carros", cars);
		}

		List<Moto> motos = motoFeignClients.getMotos(userId);

		if (motos.isEmpty()) {
			result.put("Carros", "EL usuario no tiene carros");
		} else {
			result.put("motos", motos);
		}
		
		return result;
	}

}
