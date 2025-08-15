package com.jenish.Online.Food.Ordering.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	private Long price;
	
	@ManyToOne
	private Category foodcategory;
	
	@Column(length = 1000)   /// we mentioned the length coz the url length will be high
	@ElementCollection  /// it will create the seperate table for the images
	private List<String> images;
	
	private boolean available;
	
	@ManyToOne
	private Restaurant restaurant;
	
	private boolean isVegiterian;
	
	private boolean isSeasonal;
	
	
	@ManyToMany
	private List<IngredientsItem> ingredients = new ArrayList<>();
	
	private Date createdAt;
	
}
