package com.example.blogapp.Services.Impl;

import com.example.blogapp.Entities.Category;
import com.example.blogapp.ExceptionHandling.ResourceNotFoundException;
import com.example.blogapp.Repositories.CategoryRepo;
import com.example.blogapp.Services.Interface.CategoryServiceInterface;
import com.example.blogapp.payloads.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryServiceInterface {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto addCateory(CategoryDto categoryDto) {
        Category category= modelMapper.map(categoryDto, Category.class);
        Category savedCategory= categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category= categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescp(categoryDto.getCategoryDescp());
        Category category1=categoryRepo.save(category);
        return modelMapper.map(category1,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category= categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> category=categoryRepo.findAll();
        List<CategoryDto> categoryDtos=category
                .stream()
                .map(i->modelMapper.map(i,CategoryDto.class))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category= categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        categoryRepo.delete(category);

    }
}
