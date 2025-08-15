package com.jenish.Online.Food.Ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenish.Online.Food.Ordering.model.IngredientCategory;
import com.jenish.Online.Food.Ordering.model.IngredientsItem;
import com.jenish.Online.Food.Ordering.request.IngredientCatergoryRequest;
import com.jenish.Online.Food.Ordering.request.IngredientRequest;
import com.jenish.Online.Food.Ordering.service.IngredientService;

@RestController
@RequestMapping("api/admin/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@PostMapping("/category")
	private ResponseEntity<IngredientCategory> creteIngredientCategory(
					@RequestBody IngredientCatergoryRequest req 
			) throws Exception{
		IngredientCategory item =ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());
		return new ResponseEntity<IngredientCategory>(item , HttpStatus.CREATED);
	}
	
	
	@PostMapping()
	private ResponseEntity<IngredientsItem> createIngredientItem(
					@RequestBody IngredientRequest req 
			) throws Exception{
		IngredientsItem item =ingredientService.createIngredientsItem( req.getRestaurantId() ,req.getName(), req.getCategoryId());
		return new ResponseEntity<IngredientsItem>(item , HttpStatus.CREATED);
	}
	
	
	@PostMapping("/{id}/stock")
	private ResponseEntity<IngredientsItem> updateIngredientStock(
					@PathVariable Long id
			) throws Exception{
		IngredientsItem item =ingredientService.updateStock(id);
		return new ResponseEntity<IngredientsItem>(item , HttpStatus.OK);
	}

	
	@GetMapping("restaurant/{id}")
	private ResponseEntity<List<IngredientsItem>> getrestaurantIngredient(
					@PathVariable Long id
			) throws Exception{
		List<IngredientsItem> item =ingredientService.findrestaurantIngredients(id);
		return new ResponseEntity<>(item , HttpStatus.OK);
	}
	
	@GetMapping("restaurant/{id}/category")
	private ResponseEntity<List<IngredientCategory>> getrestaurantIngredientCategory(
					@PathVariable Long id
			) throws Exception{
		List<IngredientCategory> item =ingredientService.findIngredientCategoryRestaurantId(id);
		return new ResponseEntity<>(item , HttpStatus.OK);
	}
}
