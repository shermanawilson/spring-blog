package com.codeup.Blog.controllers;

import com.codeup.Blog.models.Post;
import com.codeup.Blog.repositories.PostRepository;
import com.codeup.Blog.repositories.UserRepository;
import com.codeup.Blog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class PostController {


    private final PostRepository postDao;
    private final EmailService emailService;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, EmailService emailService, UserRepository userDao) {
        this.postDao = postDao;
        this.emailService = emailService;
        this.userDao = userDao;
    }


    @GetMapping("/index")
    public String showComments(Model model) {
        model.addAttribute("posts", postDao.findAll());


        return "index";
    }

    @PostMapping("/post")
    public String showPost(Model model) {
        List<Post> allPosts = postDao.findAll();

        model.addAttribute("posts", allPosts);
        return "index";
    }

    @GetMapping("/post/create")
    public String showCreateForm(Model model) {

        model.addAttribute("post", new Post());
        return "createPost";
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute Post post) {


        post.setOwner(userDao.getById(1L));//sets owner manually
        emailService.prepareAndSend(post,"New Post", "You created a new Post");
        postDao.save(post);

        return "redirect:/index";
    }

    @GetMapping("/post/edit/{id}")
    public String editPost(Model model, @PathVariable Long id) {

        Post singlePost = postDao.getById(id);

        model.addAttribute("post", singlePost);

        return "editPost";
    }

    @PostMapping("/post/edit/{id}")
    public String editPost(@PathVariable Long id, @ModelAttribute Post updatedPost) {


        updatedPost.setId(id);

        updatedPost.setOwner(userDao.getById(1L));

        postDao.save(updatedPost);

        return "redirect:/index";
    }


    @PostMapping("/post/delete/{id}")
    public String deletePost(Model model, @PathVariable Long id) {

        Post deletePost = postDao.getById(id);
        postDao.delete(deletePost);


        return "redirect:/index";
    }


}
