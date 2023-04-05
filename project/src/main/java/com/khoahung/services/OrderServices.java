package com.khoahung.services;

import org.springframework.stereotype.Component;

import com.khoahung.entity.Orders;

@Component
public interface OrderServices {
	public Orders getOrderById(Long id);
	public Orders createOrUpdateOrder(Orders entity);
}
