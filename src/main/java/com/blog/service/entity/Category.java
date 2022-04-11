package com.blog.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "category_table")
@Data
public class Category {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long categoryId;
	private String title;
	private String description;
	
}
