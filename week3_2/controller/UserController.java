package com.sparta.week3_2.controller;

import com.sparta.week3_2.domain.User.SignupRequestDto;
import com.sparta.week3_2.domain.comment.Comment;
import com.sparta.week3_2.security.UserDetailsImpl;
import com.sparta.week3_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@RequestBody SignupRequestDto requestDto) {
        return userService.registerUser(requestDto);
    }

    @PostMapping("/user/list")
    public List<Comment> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long id = userDetails.getUser().getId();
        return userService.getUser(id);
    }

//    @PostMapping("/user/login")
//    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        System.out.println("로그인플래그");
//        response.setHeader("Authorization","Bearer");
//
//        return "로그인";
//    }

}
