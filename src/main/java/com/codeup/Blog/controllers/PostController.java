package com.codeup.Blog.controllers;

import com.codeup.Blog.models.Post;
import com.codeup.Blog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/index")
    public String showComments(Model model){
        model.addAttribute("posts", postDao.findAll());



        return "index";
    }

    @PostMapping("/post")
    public String showPost(Model model){
        List<Post> allPosts = postDao.findAll();

        model.addAttribute("posts", allPosts);
        return "index";
    }



    @GetMapping("/post/edit/{id}")
    public String editPost(Model model, @PathVariable Long id){

        Post singlePost = postDao.getById(id);

        model.addAttribute("post", singlePost);

        return "editPost";
    }

    @PostMapping("/post/edit/{id}")
    public String editPost(
            Model model,
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String description){

        Post updatedPost = new Post(id, title, description);
        postDao.save(updatedPost);




        return "redirect:/index";
    }


    @GetMapping("/post/delete/${id}")

    @PostMapping("/post/delete/{id}")
    public String deletePost(Model model){


        return "index";
    }

}
