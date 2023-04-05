package com.khoahung.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoahung.entity.Customer;
import com.khoahung.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServicesImpl implements CustomerServices {

	@Autowired
	CustomerRepository repository;
	
	public Customer getCustomerById(Long id) 
    {
        Optional<Customer> customer = repository.findById(id);
        if(customer.isPresent()) {
            return customer.get();
        } else {
            return null;
        }
    }
	public Customer createOrUpdateCustomer(Customer entity)
    {
        Optional<Customer> customer = repository.findById(entity.getId());
         
        if(customer.isPresent()) 
        {
        	Customer newEntity = customer.get(); 
        	newEntity.setMobilephone(entity.getMobilephone());
        	newEntity.setName(entity.getName());
        	newEntity.setAddress(entity.getAddress());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
            return entity;
        }
    } 
}
