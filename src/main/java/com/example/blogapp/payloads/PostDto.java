package com.example.blogapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private int postId;

    private String title;

    private String content;

    private String imageName;

    private Date postDate;

    private CategoryDto category;

    private UserDto user;
}
