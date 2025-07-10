package com.belajar.belajarjpah2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CategoryModel {
    @Id
    private Long categoryId;
    private String categoryName;
}
