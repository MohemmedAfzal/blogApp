package com.example.blogapp.Repositories;

import com.example.blogapp.Entities.Category;
import com.example.blogapp.Entities.Post;
import com.example.blogapp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    @Query("select p from Post p where p.title like :key")
    List<Post> searchByKeyword(@Param("key") String keyword);
}
