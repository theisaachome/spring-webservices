package com.isaachome.blog.repos;

import com.isaachome.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepos extends JpaRepository<Post,Long> {
}
