package com.belajar.belajarjpah2.controller;

import com.belajar.belajarjpah2.model.CategoryModel;
import com.belajar.belajarjpah2.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    // GET all categories
    @GetMapping
    public List<CategoryModel> getAllCategories() {
        return repository.findAll();
    }

    // GET category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Long id) {
        Optional<CategoryModel> category = repository.findById(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE category
    @PostMapping
    public CategoryModel createCategory(@RequestBody CategoryModel category) {
        return repository.save(category);
    }

    // UPDATE category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryModel updatedCategory
    ) {
        Optional<CategoryModel> existingCategory = repository.findById(id);

        if (existingCategory.isPresent()) {
            CategoryModel category = existingCategory.get();
            if (updatedCategory.getCategoryName() != null) {
                category.setCategoryName(updatedCategory.getCategoryName());
            }
            CategoryModel savedCategory = repository.save(category);
            return ResponseEntity.ok(savedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
