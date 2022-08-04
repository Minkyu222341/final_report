package com.sparta.week3_2.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtPreProcessingToken extends UsernamePasswordAuthenticationToken {

    private JwtPreProcessingToken(Object principal, Object credentials) { super(principal, credentials);
        System.out.println("프로세싱토큰1");
    }

    public JwtPreProcessingToken(String token) { this(token, token.length());
        System.out.println("프로세싱토큰2");
    }
}
