package com.example.blogapp.Controllers;

import com.example.blogapp.Entities.Category;
import com.example.blogapp.Services.Interface.CategoryServiceInterface;
import com.example.blogapp.payloads.CategoryDto;
import com.example.blogapp.payloads.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryServiceInterface categoryServiceInterface;

    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(categoryServiceInterface.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/getCategories/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") int categoryId){
        return new ResponseEntity<>(categoryServiceInterface.getCategoryById(categoryId),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryDto> createUser(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1=categoryServiceInterface.addCateory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateUser(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") int categoryId){
        CategoryDto categoryDto1=categoryServiceInterface.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable("categoryId") int categoryId){
        categoryServiceInterface.deleteCategory(categoryId);
        return new ResponseEntity<>(new APIResponse("User with userId " +categoryId+ " deleted successfully",true),HttpStatus.OK);
    }
}
