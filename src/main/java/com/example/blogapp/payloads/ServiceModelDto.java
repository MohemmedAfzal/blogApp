package com.example.blogapp.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceModelDto {
    private String path;
    private String code;
    private String codeType;
    private String name;
    private String description;
}
