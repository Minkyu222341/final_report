package com.sparta.week3_2.domain.comment;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.week3_2.domain.User.User;
import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.article.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonBackReference
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;



    public Comment(CommentRequestDto commentRequestDto,Board board,User user) {
        this.text = commentRequestDto.getText();
        this.board = board;
        this.user = user;
    }

    public void update(CommentRequestDto commentRequestDto,User user) {
        this.text = commentRequestDto.getText();
        this.user = user;
    }
}
