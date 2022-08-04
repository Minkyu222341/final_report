package com.sparta.week3_2.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestTokenDto {
    private String accessToken;
    private String refreshToken;
    private int expireRefreshToken;
}
