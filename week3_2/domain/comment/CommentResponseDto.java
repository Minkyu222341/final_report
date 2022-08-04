package com.sparta.week3_2.domain.comment;

import com.sparta.week3_2.domain.User.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Setter
public class CommentResponseDto {
    private String text;
    private LocalDateTime createdAt;
    private String username;

    public CommentResponseDto(Comment comment, User user) {
        this.text = comment.getText();
        this.createdAt = comment.getCreatedAt();
        this.username = user.getUsername();
    }


}
