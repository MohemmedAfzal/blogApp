package com.example.blogapp.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="Category_Title", nullable = false, length = 255)
    private String categoryTitle;
    @Column(name="Category_Descp", nullable = false, length = 255)
    private String categoryDescp;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> post=new ArrayList<Post>();
}
