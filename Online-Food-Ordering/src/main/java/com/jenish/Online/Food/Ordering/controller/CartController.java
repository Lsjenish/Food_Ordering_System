package com.jenish.Online.Food.Ordering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenish.Online.Food.Ordering.model.Cart;
import com.jenish.Online.Food.Ordering.model.CartItem;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.request.AddCartItemRequest;
import com.jenish.Online.Food.Ordering.request.UpdateItemToCartRequest;
import com.jenish.Online.Food.Ordering.service.CartService;
import com.jenish.Online.Food.Ordering.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/cart/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req , 
												@RequestHeader("Authorization") String jwt
												) throws Exception{
		CartItem cartItem = cartService.addItemToCart(req, jwt);
		
		return new ResponseEntity<CartItem>(cartItem , HttpStatus.CREATED);
	}
	
	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItem> updateItemToCartItemQuantity(@RequestBody UpdateItemToCartRequest req , 
												@RequestHeader("Authorization") String jwt
												) throws Exception{
		CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
		
		return new ResponseEntity<CartItem>(cartItem , HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cart-item/{id}/remove")
	public ResponseEntity<Cart> removeCartItem(@PathVariable Long id, 
												@RequestHeader("Authorization") String jwt
												) throws Exception{
		Cart cart = cartService.removeItemFromCart(id, jwt);
		
		return new ResponseEntity<Cart>(cart , HttpStatus.CREATED);
	}
	
	
	@PutMapping("/cart/clear")
	public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt
												) throws Exception{
		User user = userService.findUserByJwtTocken(jwt);
		
		Cart cartItem = cartService.clearCart(user.getId());
		
		return new ResponseEntity<Cart>(cartItem , HttpStatus.CREATED);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt
												) throws Exception{
		User user = userService.findUserByJwtTocken(jwt);
		Cart cartItem = cartService.findCartByUserId(user.getId());
		
		return new ResponseEntity<Cart>(cartItem , HttpStatus.CREATED);
	}
	
}
