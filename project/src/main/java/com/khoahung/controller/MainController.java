package com.khoahung.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khoahung.dto.Customer;
import com.khoahung.dto.Orders;
import com.khoahung.services.CustomerServices;
import com.khoahung.services.OrderServices;

@RestController
public class MainController {
	
	Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	CustomerServices customerService;
	
	@Autowired
	OrderServices orderServices;
	
	
	@PostMapping(value = "/addNewOrder")
    public ResponseEntity<Orders> addNewOrder(@RequestBody Orders order) {
		
		com.khoahung.entity.Orders data = new com.khoahung.entity.Orders();
		data.setId(order.getId());
		data.setLocation(order.getLocation());
		data.setMenu(order.getMenu());
		data.setContact(order.getContact());
		data.setPrice(order.getPrice());
		
		com.khoahung.entity.Orders ret = orderServices.createOrUpdateOrder(data);
		
		Orders orders = new Orders(); 
		orders.setId(ret.getId());
		orders.setLocation(ret.getLocation());
		orders.setMenu(ret.getMenu());
		orders.setContact(ret.getContact());
		orders.setPrice(ret.getPrice());
		return new ResponseEntity<Orders>(orders, new HttpHeaders(), HttpStatus.OK);
    }
	
	@PostMapping(value = "/addNewCustomer")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer) {
		
		com.khoahung.entity.Customer data = new com.khoahung.entity.Customer();
		data.setId(customer.getId());
		data.setAddress(customer.getAddress());
		data.setName(customer.getName());
		data.setMobilephone(customer.getMobilephone());

		
		com.khoahung.entity.Customer ret = customerService.createOrUpdateCustomer(data);
		
		Customer customers = new Customer(); 
		customers.setId(ret.getId());
		customers.setAddress(ret.getAddress());
		customers.setName(ret.getName());
		customers.setMobilephone(ret.getMobilephone());
		return new ResponseEntity<Customer>(customers, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping(value = "/getOrderById/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
		
		com.khoahung.entity.Orders data = orderServices.getOrderById(id);
		if(data!=null) {
			Orders order = new Orders(); 
			order.setId(data.getId());
			order.setLocation(data.getLocation());
			order.setMenu(data.getMenu());
			order.setPrice(data.getPrice());
			order.setContact(data.getContact());
			return new ResponseEntity<Orders>(order, new HttpHeaders(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Orders>(null, new HttpHeaders(), HttpStatus.OK);
		}
    }
	@GetMapping(value = "/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		
		com.khoahung.entity.Customer data = customerService.getCustomerById(id);
		if(data!=null) {
			Customer customer = new Customer(); 
			customer.setId(data.getId());
			customer.setAddress(data.getAddress());
			customer.setName(data.getName());
			customer.setMobilephone(data.getMobilephone());
			return new ResponseEntity<Customer>(customer, new HttpHeaders(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Customer>(null, new HttpHeaders(), HttpStatus.OK);
		}
    }
}
