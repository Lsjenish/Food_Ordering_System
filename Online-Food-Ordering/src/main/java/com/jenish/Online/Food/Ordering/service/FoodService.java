package com.jenish.Online.Food.Ordering.service;

import java.util.List;

import com.jenish.Online.Food.Ordering.model.Category;
import com.jenish.Online.Food.Ordering.model.Food;
import com.jenish.Online.Food.Ordering.model.Restaurant;
import com.jenish.Online.Food.Ordering.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req  , Category category , Restaurant restaurant);
	
	
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantFood(Long restaurantId , 
										boolean isVegitarain,
										boolean isNonveg,
										boolean isSeasonal ,
										String foodCategory
										);
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailablityStatus(Long foodId) throws Exception;
	
	
}
