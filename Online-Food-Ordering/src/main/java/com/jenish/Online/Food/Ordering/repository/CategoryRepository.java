package com.jenish.Online.Food.Ordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jenish.Online.Food.Ordering.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	
	public List<Category> findByRestaurantId(Long id);
}
