package com.jenish.Online.Food.Ordering.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jenish.Online.Food.Ordering.model.Address;
import com.jenish.Online.Food.Ordering.model.Cart;
import com.jenish.Online.Food.Ordering.model.CartItem;
import com.jenish.Online.Food.Ordering.model.Order;
import com.jenish.Online.Food.Ordering.model.OrderItem;
import com.jenish.Online.Food.Ordering.model.Restaurant;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.repository.AddressRepository;
import com.jenish.Online.Food.Ordering.repository.CartRepository;
import com.jenish.Online.Food.Ordering.repository.OrderItemRepository;
import com.jenish.Online.Food.Ordering.repository.OrderRepository;
import com.jenish.Online.Food.Ordering.repository.UserRepository;
import com.jenish.Online.Food.Ordering.request.OrderRequest;


@Service
public class OrderServiceImp implements  OrderService{

	@Autowired
    private CartRepository cartRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService;

	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		Address shippAddress = order.getDeliveryAddress();
		
		
		Address savedAddress = addressRepository.save(shippAddress);
		
		if(!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}
		
		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
		
		Order createdOrder = new Order();
		
		createdOrder.setCustomer(user);
		createdOrder.setCreatedAt(new Date());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setDeliveryAddress(savedAddress);
		createdOrder.setRestaurant(restaurant);
		
		Cart cart = cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		
		Long totalPrice = cartService.calculateCartTotals(cart);
		
		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);
		
		Order savedOrder = orderRepository.save(createdOrder);
		
		restaurant.getOrders().add(savedOrder);
		
		return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		
		Order order = findOrderById(orderId);
		if(orderStatus.equals("OUT_FOR_DELIVERY") 
				|| orderStatus.equals("DELIVERED") 
				|| orderStatus.equals("COMPLETED") 
				|| orderStatus.equals("PENDING")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("please select a valid order status");

	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		Order order = findOrderById(orderId);
		
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public List<Order> getUserOrder(Long userId) {
		
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		
		if(orderStatus != null) {
			orders = orders.stream().filter(order -> 
						order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
		}
		
		return orders;
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {

		Optional<Order> opt = orderRepository.findById(orderId);
		
		if(opt.isEmpty()) {
			throw new Exception("order not found");
		}
		
		return opt.get();
	}

}
