package com.jenish.Online.Food.Ordering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenish.Online.Food.Ordering.model.Food;
import com.jenish.Online.Food.Ordering.model.Restaurant;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.request.CreateFoodRequest;
import com.jenish.Online.Food.Ordering.response.MessageResponse;
import com.jenish.Online.Food.Ordering.service.FoodService;
import com.jenish.Online.Food.Ordering.service.RestaurantService;
import com.jenish.Online.Food.Ordering.service.UserService;

@RestController
@RequestMapping("api/admin/food")
public class AdminFoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping
	public ResponseEntity<Food> createFood(
								@RequestBody CreateFoodRequest req,
								@RequestHeader("Authorization") String jwt
								) throws Exception{
		User user = userService.findUserByJwtTocken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food food = foodService.createFood(req, req.getCategory(), restaurant);
		return new ResponseEntity<Food>(food , HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(
								@PathVariable Long id,
								@RequestHeader("Authorization") String jwt
								) throws Exception{
		User user = userService.findUserByJwtTocken(jwt);
		
		foodService.deleteFood(id);
		
		MessageResponse res = new MessageResponse();
		res.setMessage("food deleted successfully");
		return new ResponseEntity<>(res , HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvailabilityStatus(
								@PathVariable Long id,
								@RequestHeader("Authorization") String jwt
								) throws Exception{
		User user = userService.findUserByJwtTocken(jwt);
		
		Food food = foodService.updateAvailablityStatus(id);
		
		return new ResponseEntity<>(food , HttpStatus.OK);
	}
}
