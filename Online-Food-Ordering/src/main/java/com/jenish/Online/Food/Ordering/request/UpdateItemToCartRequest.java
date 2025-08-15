package com.jenish.Online.Food.Ordering.request;

import lombok.Data;

@Data
public class UpdateItemToCartRequest {

	private Long cartItemId;
	
	private int quantity;
}
