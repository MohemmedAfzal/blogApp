package com.example.blogapp.Services.Interface;

import com.example.blogapp.payloads.RootCategoryDto;

import java.io.IOException;
import java.util.List;

public interface CategoryListInterface {

    List<RootCategoryDto> listAllCategories() throws IOException;

    List<RootCategoryDto> listAllSubCategories(String name) throws IOException;

    List<RootCategoryDto> listAllServices(String subCategoryName, String name) throws IOException;

    List<RootCategoryDto> getAll() throws IOException;
}
