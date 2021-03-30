package com.ibm.demo.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.ibm.demo.entity.Order;
import com.ibm.demo.repo.OrderRepository;

@Service
public class OrderService { // spring bean
	@Autowired
	OrderRepository orderRepository; 
	@Autowired
	RestTemplate getTaxesTemplate;
	
	public String createOrder(@RequestBody Order order) {
		//call getTaxes
		Float tax = getTaxesTemplate.getForObject("http//localhost:8080/getTaxes?price={price}",Float.class,order.getPrice());
		System.out.println(tax);
		order.setTax(tax);
		Order savedOrder = orderRepository.save(order);
		return savedOrder.getId();
		
	}
	public void updateOrder(@RequestBody Order order) {
		orderRepository.save(order);
	}

	public void deleteOrder(String orderId) {
		orderRepository.deleteById(orderId);
	}
}
