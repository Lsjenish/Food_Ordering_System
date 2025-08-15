package com.jenish.Online.Food.Ordering.request;

import com.jenish.Online.Food.Ordering.model.Address;

import lombok.Data;

@Data
public class OrderRequest {

	private Long  restaurantId;
	
	private Address deliveryAddress;
}
