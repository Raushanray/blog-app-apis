package com.codewithraushanblog.repositories;

import com.codewithraushanblog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {


}
