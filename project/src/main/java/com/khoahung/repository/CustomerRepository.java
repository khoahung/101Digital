package com.khoahung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khoahung.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
