package com.moto.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entity.Moto;
import com.moto.service.services.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	
	@GetMapping
	public ResponseEntity<List<Moto>> listMotos(){
		
		List<Moto> motos = motoService.getAllCars();
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> getMoto(@PathVariable("id") long id){
		
		Moto moto = motoService.getMotodById(id);
		if (moto == null) {
			return ResponseEntity.noContent().build();
		}	
		return ResponseEntity.ok(moto);
	}
	
	
	@PostMapping
	public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto){
		Moto newMoto = motoService.save(moto);
		return ResponseEntity.ok(newMoto); 
	}
	
	
	@GetMapping("/usuario/{userId}")
	public ResponseEntity<List<Moto>> listMotosByUserId(@PathVariable("userId") long userId){
		
		List<Moto> motos = motoService.byUserId(userId);
		
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);	
	}

}
