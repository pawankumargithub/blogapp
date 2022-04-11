package com.blog.service.dto;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDTO {

	private Long categoryId;
	@NotEmpty(message = "title must not be null")
	private String title;
	@NotEmpty(message = "description must not be null")
	private String description;
}
