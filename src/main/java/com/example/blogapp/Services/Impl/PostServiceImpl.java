package com.example.blogapp.Services.Impl;

import com.example.blogapp.Controllers.PostResponse;
import com.example.blogapp.Entities.Category;
import com.example.blogapp.Entities.Post;
import com.example.blogapp.Entities.User;
import com.example.blogapp.ExceptionHandling.ResourceNotFoundException;
import com.example.blogapp.Repositories.CategoryRepo;
import com.example.blogapp.Repositories.PostRepo;
import com.example.blogapp.Repositories.UserRepo;
import com.example.blogapp.Services.Interface.PostServiceInterface;
import com.example.blogapp.payloads.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostServiceInterface {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private PostRepo postRepo;
    @Override
    public PostDto createPost(PostDto postDto,int userId, int categoryId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        Post post=modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setPostDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post savedPost= postRepo.save(post);
        return modelMapper.map(savedPost, PostDto.class);




    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post= postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost= postRepo.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public PostDto getPostById(int postId) {
        Post post= postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        System.out.print(sort);
        Pageable p=PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> page=postRepo.findAll(p);
        List<Post> posts=page.getContent();
        List<PostDto> postDtos=posts.stream()
                .map(i->modelMapper.map(i,PostDto.class))
                .collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLastPage(page.isLast());
        return postResponse;
    }

    @Override
    public void deletePost(int postId) {
        Post post= postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostByCategoryId(int categoryId) {
        Category category= categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        List<Post> posts= postRepo.findByCategory(category);
        List<PostDto> postDtos=posts
                .stream()
                .map(i->modelMapper.map(i,PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }



    @Override
    public List<PostDto> getPostByUserId(int userId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post> posts= postRepo.findByUser(user);
        List<PostDto> postDtos=posts
                .stream()
                .map(i->modelMapper.map(i,PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> post= postRepo.searchByKeyword("%"+keyword+"%");
        List<PostDto> postDtos=post.stream().map(i->modelMapper.map(i,PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name= file.getOriginalFilename();

        String destination= path+ File.separator+name;

        File f=new File(path);

        if(!f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(),Paths.get(destination));


        return name;
    }

    @Override
    public InputStream downloadImage(String path, String file) throws FileNotFoundException {
        String destination= path+ File.separator+file;
        InputStream IS= new FileInputStream(destination);
        return IS;
    }
}
