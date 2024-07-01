package com.example.blogapp.Services.Interface;

import com.example.blogapp.payloads.CategoryDto;
import com.example.blogapp.payloads.UserDto;

import java.util.List;

public interface CategoryServiceInterface {

    CategoryDto addCateory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    CategoryDto getCategoryById(Integer categoryId);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Integer categoryId);
}
