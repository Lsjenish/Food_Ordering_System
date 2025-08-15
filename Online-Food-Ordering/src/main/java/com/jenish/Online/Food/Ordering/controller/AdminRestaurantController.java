package com.jenish.Online.Food.Ordering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenish.Online.Food.Ordering.model.Restaurant;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.request.CreateRestaurantRequest;
import com.jenish.Online.Food.Ordering.response.MessageResponse;
import com.jenish.Online.Food.Ordering.service.RestaurantService;
import com.jenish.Online.Food.Ordering.service.UserService;

@RestController
@RequestMapping("/api/admin/restaurant")
public class AdminRestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<Restaurant> createRestaurant(
			@RequestBody CreateRestaurantRequest createRestaurantRequest ,
			@RequestHeader("Authorization") String jwt ) throws Exception{
		User user  = userService.findUserByJwtTocken(jwt);
		Restaurant restaurant = restaurantService.createRestaurant(createRestaurantRequest, user);
		return new ResponseEntity<Restaurant>(restaurant , HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(
			@RequestBody CreateRestaurantRequest createRestaurantRequest ,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		User user  = userService.findUserByJwtTocken(jwt);
		Restaurant restaurant = restaurantService.updateRestaurant(id, createRestaurantRequest);
		return new ResponseEntity<Restaurant>(restaurant , HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteRestaurant(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		User user  = userService.findUserByJwtTocken(jwt);
		restaurantService.deleteRestaurant(id);
		
		MessageResponse res = new MessageResponse();
		res.setMessage("restaurant deleted successfully");
		return new ResponseEntity<MessageResponse>(res , HttpStatus.OK);
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Restaurant> updateRestaurantStatus(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		User user  = userService.findUserByJwtTocken(jwt);
		Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
		return new ResponseEntity<>(restaurant , HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<Restaurant> findRestaurantByUserId(
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		User user  = userService.findUserByJwtTocken(jwt);
		Restaurant restaurant = restaurantService.getRestuarantByUserId(user.getId());
		return new ResponseEntity<>(restaurant , HttpStatus.OK);
	}
	
	
}

//