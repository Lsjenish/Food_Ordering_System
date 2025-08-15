package com.jenish.Online.Food.Ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jenish.Online.Food.Ordering.model.Food;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.service.FoodService;
import com.jenish.Online.Food.Ordering.service.RestaurantService;
import com.jenish.Online.Food.Ordering.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
								@RequestHeader("Authorization") String jwt
								) throws Exception{
		User user = userService.findUserByJwtTocken(jwt);
		List<Food> food = foodService.searchFood(name);
		return new ResponseEntity<>(food , HttpStatus.CREATED);
	}
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Long restaurantId,
														@RequestParam boolean vegetarian,
														@RequestParam boolean seasonal,
														@RequestParam boolean nonveg,
														@RequestParam(required = false) String food_category,
														@RequestHeader("Authorization") String jwt
								) throws Exception{
		User user = userService.findUserByJwtTocken(jwt);
		List<Food> food = foodService.getRestaurantFood(restaurantId, vegetarian,nonveg, seasonal, food_category);
		return new ResponseEntity<>(food , HttpStatus.CREATED);
	}
}
