package com.example.blogapp.Controllers;

import com.example.blogapp.Services.Interface.CategoryListInterface;
import com.example.blogapp.Services.Interface.UserServiceInterface;
import com.example.blogapp.payloads.RootCategoryDto;
import com.example.blogapp.payloads.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserServiceInterface userServiceInterface;

    private final CategoryListInterface categoryListInterface;
    @CrossOrigin
    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userServiceInterface.getAllUsers(), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/getUsers/{userId}")
    public ResponseEntity<UserDto> getAllUsers(@PathVariable("userId") int userId) {
        return new ResponseEntity<>(userServiceInterface.getUserById(userId), HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDto1 = userServiceInterface.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }
    @CrossOrigin
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") int userId) {
        UserDto userDto1 = userServiceInterface.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable("userId") int userId) {
        userServiceInterface.deleteUser(userId);
        return new ResponseEntity<APIResponse>(new APIResponse("User with userId" + userId + "deleted successfully", true), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping(value = "/findServices", produces = "application/json")
    public List<RootCategoryDto> findServices() throws IOException {
        return categoryListInterface.getAll();
    }
    @CrossOrigin
    @GetMapping(value = "/categories", produces = "application/json")
    public List<RootCategoryDto> getRootCategory() throws IOException {
        return categoryListInterface.listAllCategories();
    }
    @CrossOrigin
    @GetMapping(value = "/subCategories/{categoryName}", produces = "application/json")
    public List<RootCategoryDto> getSubCategory(
            @PathVariable("categoryName") String categoryName
    ) throws IOException {
        return categoryListInterface.listAllSubCategories(categoryName);
    }
    @CrossOrigin
    @GetMapping(value = "/categories/{categoryName}/subCategories/{subCategoryName}/services", produces = "application/json")
    public List<RootCategoryDto> getServices(
            @PathVariable("categoryName") String categoryName,
            @PathVariable("subCategoryName") String subCategoryName
    ) throws IOException {

        return categoryListInterface.listAllServices(subCategoryName, categoryName);

    }


}
