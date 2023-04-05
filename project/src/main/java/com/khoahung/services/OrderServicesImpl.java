package com.khoahung.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoahung.entity.Orders;
import com.khoahung.repository.OrderRepository;

@Service
@Transactional
public class OrderServicesImpl implements OrderServices{

	@Autowired
	OrderRepository repository;
	
	public Orders getOrderById(Long id) 
    {
        Optional<Orders> order = repository.findById(id);
        if(order.isPresent()) {
            return order.get();
        } else {
            return null;
        }
    }
	public Orders createOrUpdateOrder(Orders entity)
    {
        Optional<Orders> order = repository.findById(entity.getId());
         
        if(order.isPresent()) 
        {
        	Orders newEntity = order.get(); 
        	newEntity.setLocation(entity.getLocation());
        	newEntity.setMenu(entity.getMenu());
        	newEntity.setContact(entity.getContact());
        	newEntity.setPrice(entity.getPrice());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
            return entity;
        }
    } 
}
