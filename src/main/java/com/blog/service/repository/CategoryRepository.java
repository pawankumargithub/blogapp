 package com.blog.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.service.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
