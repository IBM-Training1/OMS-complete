package com.ibm.demo;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.entity.Order;
import com.ibm.demo.service.OrderService;

@RestController

public class OrderController { // frontend
	@Autowired // is used for DI
	OrderService orderService; // DI

	@PostMapping("/order")
	@ResponseStatus(code = HttpStatus.CREATED)

	String createOrder(@RequestBody @Valid Order order, BindingResult bindingResult) {
		validateModel(bindingResult);
		System.out.println(order);
		return orderService.createOrder(order);
	}

	private void validateModel(Errors bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Somethign went wrong. Plesae retry");
		}
	}

	@PutMapping("/order/{id}")
	void updateorder(@PathVariable("id") String orderId, @RequestBody @Valid Order order, BindingResult bindingResult) {
		validateModel(bindingResult);
		System.out.println(orderId);
		order.setId(orderId);
		orderService.updateOrder(order);
	}

	@DeleteMapping("/order/{id}")
	void deleteorder(@PathVariable("id") String orderId) {
		System.out.println(orderId);
		orderService.deleteOrder(orderId);
	}

}
