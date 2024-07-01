package com.example.blogapp.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryDto {
    private String path;
    private String name;
    private List<ServiceModelDto> services;
}
