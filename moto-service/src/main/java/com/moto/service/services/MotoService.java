package com.moto.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entity.Moto;
import com.moto.service.repository.MotoRepository;

@Service
public class MotoService {
	
	@Autowired
	private MotoRepository motoRepository;
	
	public List<Moto> getAllCars(){
		return motoRepository.findAll();
	}
	
	public Moto getMotodById(long id) {
		return motoRepository.findById(id).orElse(null);
	}
	
	public Moto save(Moto moto) {
		Moto newMoto = motoRepository.save(moto);
		return newMoto;
	}
	
	public void remove (long id) {
		motoRepository.deleteById(id);
	}
	
	public List<Moto> byUserId(long userId){
		return motoRepository.findByUserId(userId);
		
	}

}
