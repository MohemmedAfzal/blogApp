package com.example.blogapp.ExceptionHandling;

import lombok.Data;

@Data
public class CategoryNotFoundException extends RuntimeException {
    private String categoryName;

    public CategoryNotFoundException(String categoryName) {
        super(String.format("%s name not found", categoryName));
        this.categoryName = categoryName;

    }
}
