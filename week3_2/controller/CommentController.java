package com.sparta.week3_2.controller;

import com.sparta.week3_2.domain.comment.CommentRequestDto;
import com.sparta.week3_2.domain.comment.CommentResponseDto;
import com.sparta.week3_2.security.UserDetailsImpl;
import com.sparta.week3_2.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/board/{id}/comment")
    public String addComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.commentSave(id, requestDto,userDetails);
        return "ok";
    }
//ㄴ
    @GetMapping("/board/detail/{id}/comment")
    public List<CommentResponseDto> getCommentList(@PathVariable Long id) {
        return commentService.getCommentList(id);
    }


    @GetMapping("/comment")
    public List<CommentResponseDto> getAllComment() {
        return commentService.getAll();
    }


    @DeleteMapping("/comment/{id}")
    public String commentDelete(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.delete(id,userDetails);
    }

    @PutMapping("/comment/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.update(id, commentRequestDto,userDetails);
    }
}
