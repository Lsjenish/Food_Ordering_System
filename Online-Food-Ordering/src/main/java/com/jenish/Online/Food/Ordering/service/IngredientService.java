package com.jenish.Online.Food.Ordering.service;

import java.util.List;

import com.jenish.Online.Food.Ordering.model.IngredientCategory;
import com.jenish.Online.Food.Ordering.model.IngredientsItem;

public interface IngredientService {

	public IngredientCategory createIngredientCategory(String name , Long restaurantId) throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryRestaurantId(Long id) throws Exception;
	
	public IngredientsItem createIngredientsItem(Long restaurantId , String ingredientName , Long categoryId) throws Exception;
	
	public List<IngredientsItem> findrestaurantIngredients(Long restaurantId) throws Exception;
	
	public IngredientsItem updateStock(Long id) throws Exception;
	
}
