package com.sparta.week3_2.domain.article;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.week3_2.domain.User.User;
import com.sparta.week3_2.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

//    mappedBy = "board",cascade = CascadeType.REMOVE mappedBy = "board",
    @JsonManagedReference
    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    public Board(BoardRequestDto boardRequestDto,User user) {
        this.content = boardRequestDto.getContent();
        this.title = boardRequestDto.getTitle();
        this.user = user;
    }

    public void update(BoardRequestDto boardRequestDto,User user) {
        this.content = boardRequestDto.getContent();
        this.title = boardRequestDto.getTitle();
        this.user = user;
    }
}