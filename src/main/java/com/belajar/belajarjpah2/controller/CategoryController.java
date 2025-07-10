package com.belajar.belajarjpah2.controller;

import com.belajar.belajarjpah2.model.CategoryModel;
import com.belajar.belajarjpah2.repository.CategoryRepository;
import com.belajar.belajarjpah2.utils.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private final CategoryRepository repository;
    private MessageModel messageModel;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    // GET all categories
    @GetMapping
    public ResponseEntity<MessageModel> getAllCategories(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "", required = false) String nameCategory
    ) {
        messageModel = new MessageModel();
        Pageable pageRequest = PageRequest.of(page, size);
        Page<CategoryModel> data;

        data = repository.findAllByCategoryNameContaining(nameCategory,pageRequest);
        messageModel.setStatus(true);
        messageModel.setData(data.getContent());
        messageModel.setTotalPages(data.getTotalPages());
        messageModel.setCurrentPage(data.getNumber());
        messageModel.setTotalItems((int) data.getTotalElements());
        messageModel.setNumberOfElement(data.getNumberOfElements());
        if (data.getContent().isEmpty()) {
            messageModel.setMessage("Data Kosong");
        } else {
            messageModel.setMessage("Data Berhasil Di Get");
        }
        return ResponseEntity.status(HttpStatus.OK).body(messageModel);
    }

    // GET category by id
    @GetMapping("/{id}")
    public ResponseEntity<MessageModel> getCategoryById(@PathVariable Long id) {
        messageModel = new MessageModel();

        Optional<CategoryModel> category = repository.findById(id);
        messageModel.setStatus(true);
        if (category.isEmpty()) {
            messageModel.setMessage("Data Kosong");
        } else {
            messageModel.setMessage("Data Tidak Ditemukan");
        }
        messageModel.setData(category);

        return ResponseEntity.status(HttpStatus.OK).body(messageModel);
    }

    // CREATE category
    @PostMapping
    public ResponseEntity<MessageModel> createCategory(@RequestBody CategoryModel category) {
        messageModel = new MessageModel();
        messageModel.setStatus(true);
        messageModel.setMessage("Data Berhasil Di Update");
        messageModel.setData(category);
        repository.save(category);
        return ResponseEntity.status(HttpStatus.OK).body(messageModel);
    }

    // UPDATE category
    @PutMapping("/{id}")
    public ResponseEntity<MessageModel> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryModel updatedCategory
    ) {
        messageModel = new MessageModel();
        Optional<CategoryModel> existingCategory = repository.findById(id);

        if (existingCategory.isPresent()) {
            CategoryModel category = existingCategory.get();
            if (updatedCategory.getCategoryName() != null) {
                category.setCategoryName(updatedCategory.getCategoryName());
            }
            CategoryModel savedCategory = repository.save(category);
            messageModel.setStatus(true);
            messageModel.setMessage("Data Berhasil Di Update");
            messageModel.setData(savedCategory);
        } else {
            messageModel.setStatus(false);
            messageModel.setMessage("Data Gagal Di Update");
        }
        return ResponseEntity.status(HttpStatus.OK).body(messageModel);
    }

    // DELETE category
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageModel> deleteCategory(@PathVariable Long id) {
        messageModel = new MessageModel();
        if (repository.existsById(id)) {
            repository.deleteById(id);
            messageModel.setStatus(true);
            messageModel.setMessage("Data Berhasil Di Delete");
        } else {
            messageModel.setStatus(false);
            messageModel.setMessage("Data Tidak Di temukan");
        }
        return ResponseEntity.status(HttpStatus.OK).body(messageModel);
    }
}
