package com.codeup.Blog.repositories;

import com.codeup.Blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {

    Post getByDescription(String description);


    Post getById(String id);
}
