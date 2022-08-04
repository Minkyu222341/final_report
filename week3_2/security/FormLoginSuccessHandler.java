package com.sparta.week3_2.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.sparta.week3_2.security.jwt.JwtTokenUtils;
import com.sparta.week3_2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.sparta.week3_2.security.jwt.JwtTokenUtils.CLAIM_EXPIRED_DATE;
import static com.sparta.week3_2.security.jwt.JwtTokenUtils.JWT_SECRET;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String AUTH_HEADER = "Authorization";
    public static final String ACCESS_TOKEN = " Access-Token";
    public static final String REFRESH_HEADER = "Refresh-Token";

    @Autowired
    private AuthService service;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        // Token 생성
        final String accessToken = JwtTokenUtils.generateJwtToken(userDetails);
        final String refreshToken = JwtTokenUtils.generateRefreshToken(userDetails);

        saveRefreshTokenInfo(accessToken, refreshToken);
        responseSetHeader(response, accessToken, refreshToken);

        System.out.println("Success핸들러");
    }
    private void saveRefreshTokenInfo(String accessToken, String refreshToken) {
        DecodedJWT jwt = null;
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verify = verifier.verify(refreshToken);
        jwt = verify;
        DecodedJWT decodedJWT = jwt;
        Date expiredDate = decodedJWT.getClaim(CLAIM_EXPIRED_DATE).asDate();
        service.saveToken(accessToken, refreshToken,expiredDate);
    }

    private void responseSetHeader(HttpServletResponse response, String accessToken, String refreshToken) {
        response.addHeader(AUTH_HEADER, ACCESS_TOKEN + " " + accessToken);
        response.addHeader(REFRESH_HEADER, refreshToken);
    }
}

//    String name = authentication.getName();
//    String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();