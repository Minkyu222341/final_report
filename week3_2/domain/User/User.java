package com.sparta.week3_2.domain.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
//@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

//    mappedBy = "user",cascade = CascadeType.REMOVE
//    ,fetch = FetchType.EAGER mappedBy = "user",
    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Board> board;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Comment> comment;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

//    public void addPostToUser(Board board) {
//        this.board.add(board);
//    }

}
