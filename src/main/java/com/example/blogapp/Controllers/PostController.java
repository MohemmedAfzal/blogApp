package com.example.blogapp.Controllers;

import com.example.blogapp.Services.Interface.PostServiceInterface;
import com.example.blogapp.payloads.PostDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostServiceInterface postServiceInterface;

    @Value("${project.image}")
    private String path;

    @GetMapping("/post")
    public ResponseEntity<PostResponse> getPost(
            @RequestParam(value ="pageNumber", defaultValue = "0", required = false)int pageNumber,
            @RequestParam(value ="pageSize", defaultValue = "5", required = false)int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false)String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false)String sortDir
    ){
        PostResponse postResponse=postServiceInterface.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") int postId){
        return new ResponseEntity<>(postServiceInterface.getPostById(postId),HttpStatus.OK);
    }
    @GetMapping("/post/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable("userId") int userId){
        List<PostDto> postDtos=postServiceInterface.getPostByUserId(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("/post/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable("categoryId") int categoryId){
        List<PostDto> postDtos=postServiceInterface.getPostByCategoryId(categoryId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @PostMapping("/user/{userId}/category/{categoryId}/post/save")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,
                                              @PathVariable("userId") int userId,
                                              @PathVariable("categoryId") int categoryId) {
        PostDto postDto1=postServiceInterface.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }
    @PutMapping("/post/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto
            ,@PathVariable("postId")int postId){
        PostDto postDto1=postServiceInterface.updatePost(postDto,postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }
    @DeleteMapping("/post/delete/{postId}")
    public  ResponseEntity<APIResponse> deletePost(@PathVariable("postId") int postId){
        postServiceInterface.deletePost(postId);
        return new ResponseEntity<>(new APIResponse("Post with PostId "+postId+" Deleted Successfully", true),HttpStatus.OK);
    }
    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDto>> search(@PathVariable("keyword") String keyword){
        List<PostDto> post=postServiceInterface.searchPost(keyword);
        return new ResponseEntity<>(post, HttpStatus.OK);
        }

        @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImageController(
            @RequestParam("image") MultipartFile image,
            @PathVariable("postId") int postId
        ) {

            String Filename = null;
            PostDto updatedPostDto = null;
            try {
                PostDto postDto = postServiceInterface.getPostById(postId);
                Filename = postServiceInterface.uploadImage(path, image);
                postDto.setImageName(Filename);
                updatedPostDto = postServiceInterface.updatePost(postDto, postId);
            } catch (IOException e) {
                //throw new RuntimeException(e);
                System.out.print("Error " + e.getMessage());
                return new ResponseEntity<>
                        (updatedPostDto, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
        }

        @GetMapping("/download/{imageName}")
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
        ) throws IOException {
            InputStream IS= postServiceInterface.downloadImage(path,imageName);
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            StreamUtils.copy(IS,response.getOutputStream());
        }
}
