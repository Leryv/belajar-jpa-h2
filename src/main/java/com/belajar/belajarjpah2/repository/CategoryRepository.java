package com.belajar.belajarjpah2.repository;

import com.belajar.belajarjpah2.model.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    @Query("SELECT c FROM CategoryModel c WHERE LOWER(c.categoryName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<CategoryModel> findAllByCategoryNameContaining(@Param("name") String name, Pageable pageable);

}
