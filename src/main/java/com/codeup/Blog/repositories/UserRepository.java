package com.codeup.Blog.repositories;

import com.codeup.Blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
