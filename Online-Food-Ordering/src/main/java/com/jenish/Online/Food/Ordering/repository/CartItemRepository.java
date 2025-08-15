package com.jenish.Online.Food.Ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jenish.Online.Food.Ordering.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
