package com.khoahung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khoahung.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{

}
