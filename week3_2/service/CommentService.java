package com.sparta.week3_2.service;

import com.sparta.week3_2.domain.User.User;
import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.comment.Comment;
import com.sparta.week3_2.domain.comment.CommentRequestDto;
import com.sparta.week3_2.domain.comment.CommentResponseDto;
import com.sparta.week3_2.repository.BoardRepository;
import com.sparta.week3_2.repository.CommentRepository;
import com.sparta.week3_2.repository.UserRepository;
import com.sparta.week3_2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    @Transactional
    public String commentSave(Long id, CommentRequestDto dto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));
        User user = userDetails.getUser();
        log.info("Service 값 테스트 내용={}", dto.getText());
        Comment comment = new Comment(dto,board,user);

        commentRepository.save(comment);

        return "ok";
    }

    public List<CommentResponseDto> getCommentList(Long id) {
        List<Comment> comments = commentRepository.findByBoard_Id(id);
        userRepository.findById(id);
        List<CommentResponseDto> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            User user = comment.getUser();
            System.out.println("댓글유저네임 : "+user.getUsername());
            dtos.add(new CommentResponseDto(comment,user));
        }
        return dtos;
    }

    public List<CommentResponseDto> getAll() {
        List<Comment> all = commentRepository.findAll();
        List<CommentResponseDto> dtos = new ArrayList<>();
        for (Comment comment : all) {
            User user = comment.getUser();
            dtos.add(new CommentResponseDto(comment,user));
        }
        return dtos;
    }

    public String delete(Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지않음"));

        if (comment.getUser().getUsername().equals(userDetails.getUsername())) {
            commentRepository.deleteById(comment.getId());
            return comment.getId()+" 삭제 ";
        }
        return "작성자가 아닙니다";
    }

    @Transactional
    public String update(Long id, CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지않음"));
        if (comment.getUser().getUsername().equals(userDetails.getUsername())) {
            User user = userDetails.getUser();
            comment.update(commentRequestDto,user);
            return comment.getId() + " 수정";
        }
        return "작성자가 아닙니다";
    }
}
