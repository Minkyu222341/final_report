package com.sparta.week3_2.controller;

import com.sparta.week3_2.domain.article.BoardRequestDto;
import com.sparta.week3_2.domain.article.BoardResponseDto;
import com.sparta.week3_2.security.UserDetailsImpl;
import com.sparta.week3_2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {


    private final BoardService boardService;

    @GetMapping("/board")
        public List<BoardResponseDto> getList() {
        return boardService.getBoardList();
    }

    @GetMapping("/board/detail/{id}")
    public BoardResponseDto detail(@PathVariable Long id) {
        return boardService.detailPost(id);
    }

    @PostMapping("/auth/board")
    public String addPost(@RequestBody BoardRequestDto boardRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("저장 컨트롤러 : "+boardRequestDto.getTitle());
        log.info("Post요청={}","ADDPOST 실행");

        boardService.savePost(boardRequestDto,userDetails);
        return "ok";
    }

    @PutMapping("/auth/board/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.update(id, boardRequestDto,userDetails);
    }

    @DeleteMapping("/auth/board/{id}")
    public String delPost(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return boardService.delete(id,userDetails);
    }

}

