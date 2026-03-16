package com.inventra.inventra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventra.inventra.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}