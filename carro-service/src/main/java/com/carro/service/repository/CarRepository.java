package com.carro.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carro.service.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	List<Car> findByUserId(long userId);
}
