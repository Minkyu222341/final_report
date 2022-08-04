package com.sparta.week3_2.repository;

import com.sparta.week3_2.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard_Id(Long id);
}
