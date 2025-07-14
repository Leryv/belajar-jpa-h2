package com.belajar.belajarjpah2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class CategoryModel {
    @Id
    private Long categoryId;
    private String categoryName;
}
