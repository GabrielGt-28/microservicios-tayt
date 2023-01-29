package com.usuario.service.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.model.Moto;

@FeignClient(name = "moto-service", url = "http://localhost:8083")
@RequestMapping("/moto")
public interface MotoFeignClients {
	
	@PostMapping()
	public Moto saveMoto(@RequestBody Moto moto);
	
	@GetMapping("/usuario/{userId}")
	public List<Moto> getMotos(@PathVariable("userId") long userId);

}
