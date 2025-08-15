package com.jenish.Online.Food.Ordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jenish.Online.Food.Ordering.model.IngredientCategory;
import com.jenish.Online.Food.Ordering.model.IngredientsItem;
import com.jenish.Online.Food.Ordering.model.Restaurant;
import com.jenish.Online.Food.Ordering.repository.IngredientCategoryRepository;
import com.jenish.Online.Food.Ordering.repository.IngredientItemRepository;

@Service
public class IngredientServiceImp implements IngredientService{

	@Autowired
	private IngredientItemRepository ingredientItemRepository;
	
	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;
	
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public IngredientCategory createIngredientCategory(String name,
			Long restaurantId) throws Exception {
		
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		
		IngredientCategory category = new IngredientCategory();
		
		category.setRestaurant(restaurant);
		category.setName(name);
		
		return ingredientCategoryRepository.save(category);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("ingredient category not found");
		}
		return opt.get();
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryRestaurantId(Long id)
			throws Exception {
		restaurantService.findRestaurantById(id);
		return ingredientCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		
		IngredientCategory category = findIngredientCategoryById(categoryId);
		
		IngredientsItem item = new IngredientsItem();
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);
		
		IngredientsItem ingredientsItem = ingredientItemRepository.save(item);
		category.getIngredientsIntem().add(ingredientsItem);
		return ingredientsItem;
	}

	@Override
	public List<IngredientsItem> findrestaurantIngredients(Long restaurantId) throws Exception {
		
		return ingredientItemRepository.findByRestaurantId(restaurantId);
		
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		Optional<IngredientsItem> opt = ingredientItemRepository.findById(id);
		if(opt.isEmpty()) {
			throw new Exception("ingredient not found...");
		}
		IngredientsItem item = opt.get();
		item.setInStock(!item.isInStock());
		return ingredientItemRepository.save(item);
	}
	
	
}
