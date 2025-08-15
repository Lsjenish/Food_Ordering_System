package com.jenish.Online.Food.Ordering.service;

import java.util.List;

import com.jenish.Online.Food.Ordering.model.Order;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.request.OrderRequest;

public interface OrderService {

	public Order createOrder(OrderRequest order , User user) throws Exception;
	
	public Order updateOrder(Long orderId , String orderStatus) throws Exception;
	
	public void cancelOrder(Long orderId) throws Exception;
	
	public List<Order> getUserOrder(Long userId);
	
	public List<Order> getRestaurantOrder(Long restaurantId , String orderStatus) throws Exception;
	
	public Order findOrderById(Long orderId) throws Exception;
}
