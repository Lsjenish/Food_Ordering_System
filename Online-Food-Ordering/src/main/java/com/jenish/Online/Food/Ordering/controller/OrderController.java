package com.jenish.Online.Food.Ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenish.Online.Food.Ordering.model.Order;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.request.OrderRequest;
import com.jenish.Online.Food.Ordering.service.OrderService;
import com.jenish.Online.Food.Ordering.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/order")
	public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req , 
												@RequestHeader("Authorization") String jwt
												) throws Exception{
		User user = userService.findUserByJwtTocken(jwt);
		Order order = orderService.createOrder(req, user);
		
		return new ResponseEntity<Order>(order , HttpStatus.CREATED);
	}
	
	
	@GetMapping("/order/user")
	public ResponseEntity<List<Order>> getOrderHistory(
												@RequestHeader("Authorization") String jwt
												) throws Exception{
		User user = userService.findUserByJwtTocken(jwt);
		List<Order> orders = orderService.getUserOrder(user.getId());
		
		return new ResponseEntity<List<Order>>(orders , HttpStatus.CREATED);
	}
	
}
