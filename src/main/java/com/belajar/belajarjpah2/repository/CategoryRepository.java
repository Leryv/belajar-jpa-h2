package com.belajar.belajarjpah2.repository;

import com.belajar.belajarjpah2.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
