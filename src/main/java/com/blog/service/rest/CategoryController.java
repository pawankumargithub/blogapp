package com.blog.service.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.demo.service.CategoryService;
import com.blog.service.dto.CategoryDTO;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDTO));
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<CategoryDTO>> findAllCategories() {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAllCategories());
	}
	
	@GetMapping("/find-one/{id}")
	public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long id){	
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable Long id){
		categoryService.deleteById(id);
		return new ResponseEntity<>("category deleted:"+id ,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryDTO,id));
	}
}
