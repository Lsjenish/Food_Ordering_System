package com.jenish.Online.Food.Ordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jenish.Online.Food.Ordering.model.Category;
import com.jenish.Online.Food.Ordering.model.Restaurant;
import com.jenish.Online.Food.Ordering.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@Override
	public Category createCategory(String name, Long userId) throws Exception {
		Restaurant restaurant = restaurantService.getRestuarantByUserId(userId);
		
		Category category = new Category();
		category.setName(name);
		category.setRestaurant(restaurant);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		Restaurant restaurant = restaurantService.getRestuarantByUserId(id);
		return categoryRepository.findByRestaurantId(id);
	}

	@Override
	public Category findCategoryById(Long Id) throws Exception {
		Optional<Category> opt = categoryRepository.findById(Id);
		
		if(opt.isEmpty()) {
			throw new Exception("category not found");
		}
		return opt.get();
	}

	
}
