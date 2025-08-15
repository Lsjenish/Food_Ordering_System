package com.jenish.Online.Food.Ordering.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jenish.Online.Food.Ordering.model.Cart;
import com.jenish.Online.Food.Ordering.model.CartItem;
import com.jenish.Online.Food.Ordering.model.Food;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.repository.CartItemRepository;
import com.jenish.Online.Food.Ordering.repository.CartRepository;
import com.jenish.Online.Food.Ordering.request.AddCartItemRequest;

@Service
public class CartServiceImp implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private FoodService foodService;

	@Override
	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
		User user = userService.findUserByJwtTocken(jwt);
		
		Food food = foodService.findFoodById(req.getFoodId());
		
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		for(CartItem cartItem : cart.getItems()) {
			if(cartItem.getFood().equals(food)) {
				int newQuantity = cartItem.getQuantity() + req.getQuantity();
				return updateCartItemQuantity(cartItem.getId(), newQuantity);
			}
		}
		
		CartItem newCartItem = new CartItem();
		newCartItem.setFood(food);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setIngredients(req.getIngredients());
		newCartItem.setTotalPrice(req.getQuantity() * food.getPrice());
		
		CartItem savedCarItem = cartItemRepository.save(newCartItem);
		
		cart.getItems().add(savedCarItem);
		return savedCarItem;
	}

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
		
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		
		if(cartItem.isEmpty()) {
			throw new Exception("cart item not found");
		}
		
		CartItem item = cartItem.get();
		
		item.setQuantity(quantity);
		
		
		
		item.setTotalPrice(item.getFood().getPrice() * quantity);
		
		return cartItemRepository.save(item);
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
		User user = userService.findUserByJwtTocken(jwt);
		
		Cart cart = cartRepository.findByCustomerId(user.getId());
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		
		if(cartItem.isEmpty()) {
			throw new Exception("cart item not found");
		}
		
		CartItem item = cartItem.get();
		
		cart.getItems().add(item);
		return cartRepository.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		Long total = 0L;
		
		for(CartItem cartItem : cart.getItems()) {
			total += cartItem.getFood().getPrice() * cartItem.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		Optional<Cart> optionalCart = cartRepository.findById(id);
		
		if(optionalCart.isEmpty()) {
			throw new Exception("cart not found with id " + id);
		}
		return optionalCart.get();
	}

	@Override
	public Cart findCartByUserId(Long userId) throws Exception {
//		User user = userService.findUserByJwtTocken(jwt);
		Cart cart =  cartRepository.findByCustomerId(userId);
		cart.setTotal(calculateCartTotals(cart));
		return cart;
	}

	@Override
	public Cart clearCart(Long userId) throws Exception {
//		User user = userService.findUserByJwtTocken(jwt);
		Cart cart = findCartByUserId(userId);
		
		cart.getItems().clear();
		return cartRepository.save(cart);
	}

}
