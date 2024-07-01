package com.example.blogapp.Repositories;

import com.example.blogapp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
