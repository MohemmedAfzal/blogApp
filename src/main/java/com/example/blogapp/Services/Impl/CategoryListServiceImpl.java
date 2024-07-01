package com.example.blogapp.Services.Impl;

import com.example.blogapp.Services.Interface.CategoryListInterface;
import com.example.blogapp.payloads.RootCategoryDto;
import com.example.blogapp.payloads.SubCategoryDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryListServiceImpl implements CategoryListInterface {

    @Override
    public List<RootCategoryDto> listAllCategories() throws IOException {
        return getData().stream().map(this::rootCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<RootCategoryDto> listAllSubCategories(@NonNull final String category) throws IOException {
        final List<RootCategoryDto> rootCategoryDtoList = getData().stream().filter(p -> p.getName().equalsIgnoreCase(category.trim())).collect(Collectors.toList());
        rootCategoryDtoList.forEach(r -> {
            List<SubCategoryDto> result = r.getSubCategories().stream().map(this::subCategoryDto).collect(Collectors.toList());
            r.setSubCategories(result);
        });
        return rootCategoryDtoList;
    }

    @Override
    public List<RootCategoryDto> listAllServices(@NonNull final String subCategory, @NonNull final String category) throws IOException {
        final List<RootCategoryDto> rootCategoryDtoList = getData().stream().filter(p -> p.getName().equalsIgnoreCase(category.trim())).collect(Collectors.toList());
        rootCategoryDtoList.forEach(r -> {
            List<SubCategoryDto> result = r.getSubCategories().stream().filter(p -> p.getName().equalsIgnoreCase(subCategory.trim())).collect(Collectors.toList());
            r.setSubCategories(result);
        });
        return rootCategoryDtoList;
    }

    @Override
    public List<RootCategoryDto> getAll() throws IOException {
        return getData();
    }

    private RootCategoryDto rootCategoryDto(RootCategoryDto r) {
        final RootCategoryDto rootCategoryDto = new RootCategoryDto();
        rootCategoryDto.setName(r.getName());
        rootCategoryDto.setPath(r.getPath());
        return rootCategoryDto;
    }

    private SubCategoryDto subCategoryDto(SubCategoryDto s) {
        final SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setName(s.getName());
        subCategoryDto.setPath(s.getPath());
        return subCategoryDto;
    }

    private List<RootCategoryDto> getData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<RootCategoryDto>> typeReference = new TypeReference<List<RootCategoryDto>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/services.json");
        return mapper.readValue(inputStream, typeReference);
    }

}
