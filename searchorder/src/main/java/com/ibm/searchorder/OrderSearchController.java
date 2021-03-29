package com.ibm.searchorder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.ibm.searchorder.OrderService;
import com.ibm.searchorder.Order;

@RestController
public class OrderSearchController {
	@Autowired //is used for DI
	OrderService orderService;   // DI
	Order order;
	
	
	@GetMapping("/order")
	List<Order> getOrders() {
		return orderService.getOrders();
	}
	@GetMapping("/order/{id}")
	Optional<Order> getOrderById(@PathVariable ("id") String orderId) {
		return orderService.getOrderById(orderId);
	}

}
