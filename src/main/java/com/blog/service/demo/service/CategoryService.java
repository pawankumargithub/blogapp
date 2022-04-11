package com.blog.service.demo.service;

import java.util.List;

import com.blog.service.dto.CategoryDTO;

public interface CategoryService {

	CategoryDTO createCategory(CategoryDTO categoryDTO);

	CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

	CategoryDTO findById(Long id);

	void deleteById(Long id);

	List<CategoryDTO> findAllCategories();

}
