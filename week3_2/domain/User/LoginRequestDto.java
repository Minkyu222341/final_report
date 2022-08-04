package com.sparta.week3_2.domain.User;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class LoginRequestDto {
    private String username;
    private String password;
}

