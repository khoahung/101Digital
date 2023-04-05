package com.khoahung.services;

import org.springframework.stereotype.Component;

import com.khoahung.entity.Customer;

@Component
public interface CustomerServices {
	public Customer getCustomerById(Long id);
	public Customer createOrUpdateCustomer(Customer entity);
}
