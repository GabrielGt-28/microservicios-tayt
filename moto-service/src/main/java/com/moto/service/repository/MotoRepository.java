package com.moto.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moto.service.entity.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long>{
		
	List<Moto> findByUserId(long userId);

}
