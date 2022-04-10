package com.blog.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
