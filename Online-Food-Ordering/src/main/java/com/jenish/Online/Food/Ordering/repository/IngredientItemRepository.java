package com.jenish.Online.Food.Ordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jenish.Online.Food.Ordering.model.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long>{

	List<IngredientsItem> findByRestaurantId(Long id);
}
