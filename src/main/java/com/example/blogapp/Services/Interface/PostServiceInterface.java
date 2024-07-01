package com.example.blogapp.Services.Interface;

import com.example.blogapp.Controllers.PostResponse;
import com.example.blogapp.Entities.Post;
import com.example.blogapp.payloads.CategoryDto;
import com.example.blogapp.payloads.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface PostServiceInterface {

    PostDto createPost(PostDto postDto, int userId, int categoryId);

    PostDto updatePost(PostDto postDto, int postId);

    PostDto getPostById(int postId);

    PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy, String sortDir);

    void deletePost(int postId);

    List<PostDto> getPostByCategoryId(int categoryId);

    List<PostDto> getPostByUserId(int userId);

    List<PostDto> searchPost(String keyword);

    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream downloadImage(String path, String file) throws FileNotFoundException;
}
