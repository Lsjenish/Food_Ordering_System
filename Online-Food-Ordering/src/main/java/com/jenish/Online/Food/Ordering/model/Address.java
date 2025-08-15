package com.jenish.Online.Food.Ordering.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}
