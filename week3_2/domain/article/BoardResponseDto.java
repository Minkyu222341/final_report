package com.sparta.week3_2.domain.article;

import com.sparta.week3_2.domain.comment.CommentResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private String username;
    private List<CommentResponseDto> comment;

    public BoardResponseDto(Board board,String username) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.createdAt = board.getCreatedAt();
        this.username = username;
    }

    public BoardResponseDto(Board board, String username, List<CommentResponseDto> comment) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.createdAt = board.getCreatedAt();
        this.username = username;
        this.comment = comment;
    }


}
