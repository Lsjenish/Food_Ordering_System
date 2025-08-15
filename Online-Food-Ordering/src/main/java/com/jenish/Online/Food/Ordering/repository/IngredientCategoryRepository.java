package com.jenish.Online.Food.Ordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jenish.Online.Food.Ordering.model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long>{

	List<IngredientCategory> findByRestaurantId(Long Id);
}
