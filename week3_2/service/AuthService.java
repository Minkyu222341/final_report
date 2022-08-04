package com.sparta.week3_2.service;

import com.sparta.week3_2.domain.auth.RefreshToken;
import com.sparta.week3_2.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public String saveToken(String accessToken, String refreshToken, Date expireRefreshToken) {
        RefreshToken tokenInfo = new RefreshToken(accessToken, refreshToken, expireRefreshToken);
        authRepository.save(tokenInfo);
        return "토큰저장완료";
    }
}
