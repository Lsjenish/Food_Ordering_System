package com.jenish.Online.Food.Ordering.service;

import java.util.List;

import com.jenish.Online.Food.Ordering.dto.RestaurantDto;
import com.jenish.Online.Food.Ordering.model.Restaurant;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.request.CreateRestaurantRequest;

public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantRequest req , User user);
	
	public Restaurant updateRestaurant(Long restaurantId , CreateRestaurantRequest updatedRestaurant) throws Exception;
	
	public void deleteRestaurant(Long restaurantId ) throws Exception;
	
	public List<Restaurant> getAllRestaurant();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws Exception;
	
	public Restaurant getRestuarantByUserId(Long userId) throws Exception;
	
	public RestaurantDto addToFavorates(Long restaurantId , User user) throws Exception;
		
	public Restaurant updateRestaurantStatus(Long id) throws Exception;
	
	
}

//
