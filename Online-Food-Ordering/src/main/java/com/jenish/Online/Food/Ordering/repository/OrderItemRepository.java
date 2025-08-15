package com.jenish.Online.Food.Ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jenish.Online.Food.Ordering.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
