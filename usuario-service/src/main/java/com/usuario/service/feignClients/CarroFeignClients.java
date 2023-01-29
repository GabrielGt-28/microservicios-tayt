package com.usuario.service.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.model.Car;


@FeignClient(name = "carro-service", url = "http://localhost:8082")
@RequestMapping("/carro")
public interface CarroFeignClients {
	
	@PostMapping
	public Car save(@RequestBody Car car);
	
	@GetMapping("/usuario/{userId}")
	public List<Car> getCars(@PathVariable("userId") long userId);

}
