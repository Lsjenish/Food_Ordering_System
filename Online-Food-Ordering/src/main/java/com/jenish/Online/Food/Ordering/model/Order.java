package com.jenish.Online.Food.Ordering.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "`order`")
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User customer;
	
	@ManyToOne
	@JsonIgnore
	private Restaurant restaurant;
	
	private Long totalAmount;
	
	private String orderStatus;
	
	private Date createdAt;
	
	@ManyToOne
	private Address deliveryAddress;
	
	@OneToMany
	private List<OrderItem> items;
	
	private int totalItem;
	
	private Long totalPrice;
	
//	private Payment payment;
	
}
