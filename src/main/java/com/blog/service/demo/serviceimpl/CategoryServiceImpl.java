package com.blog.service.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.service.demo.service.CategoryService;
import com.blog.service.dto.CategoryDTO;
import com.blog.service.entity.Category;
import com.blog.service.exception.CategoryNotFoundException;
import com.blog.service.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = toEntity(categoryDTO);
		categoryRepository.save(category);
		return toDto(category);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("category with id:" + id + " not existed"));
		//category.setCategoryId(categoryDTO.getCategoryId());
		category.setTitle(categoryDTO.getTitle());
		category.setDescription(categoryDTO.getDescription());
		categoryRepository.save(category);

		return toDto(category);
	}

	@Override
	public CategoryDTO findById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("category with id:" + id + " not existed"));
		return toDto(category);
	}

	@Override
	public void deleteById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("category with id:" + id + " not existed"));
		
		categoryRepository.delete(category);
	}

	@Override
	public List<CategoryDTO> findAllCategories() {
		List<CategoryDTO> categoryDTOs = categoryRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
		if(categoryDTOs.isEmpty()) {
			throw new CategoryNotFoundException("category is empty");
		}
		return categoryDTOs;
	}
	
	private Category toEntity(CategoryDTO categoryDTO) {
		return mapper.map(categoryDTO, Category.class);
	}
	
	private CategoryDTO toDto(Category category) {
		return mapper.map(category, CategoryDTO.class);
	}

}
