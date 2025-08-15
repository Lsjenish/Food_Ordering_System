package com.jenish.Online.Food.Ordering.service;

import java.util.List;

import com.jenish.Online.Food.Ordering.model.Category;

public interface CategoryService {

	public Category createCategory(String name , Long userId) throws Exception;
	
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception;
	
	public Category findCategoryById(Long Id) throws Exception;
}
