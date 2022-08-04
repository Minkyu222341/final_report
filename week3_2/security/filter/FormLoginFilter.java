package com.sparta.week3_2.security.filter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Slf4j
                // UsernamePasswordAuthenticationFilter가 상속한 AbstractAuthenticationProcessingFilter에 있는 AuthenticationManager객체를 가져옴
public class FormLoginFilter extends UsernamePasswordAuthenticationFilter {
    final private ObjectMapper objectMapper;
    // AuthenticationManager = AuthenticationProvider라는 클래스 객체를 관리하는데 authenticate()메소드를 호출해서 인증이 완료된 객체를 반환한다.
    public FormLoginFilter(final AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //UsernamePasswordAuthenticationToken < AbstractAuthenticationToken < Authentication
        //UsernamePasswordAuthenticationToken = 인증이 끝나고 SecurityContextHolder.getContext()에 등록 될 Authentication 객체(유저)
        UsernamePasswordAuthenticationToken authRequest;
        System.out.println("폼로그인필터");

        //로그인시 requestBody에서 username과 password를 파싱해서
        try {
            JsonNode requestBody = objectMapper.readTree(request.getInputStream());
            String username = requestBody.get("username").asText();
            String password = requestBody.get("password").asText();
            System.out.println(username+"  "+password);
            //UsernamePasswordAuthenticationToken에서 입력받은 아이디와 비밀번호를 받아서 아직 인증이 완료되지 않은 Authentication 객체를 생성
            authRequest = new UsernamePasswordAuthenticationToken(username, password);

            //생성하는 과정에서 오류가 있다면(username, password 값이 없다면) Exception 호출
        } catch (Exception e) {
            throw new RuntimeException("username, password 입력이 필요합니다. (JSON)");
        }
    // 요청을위한 가공 ?
          setDetails(request, authRequest);

        /*  AuthenticationManager가 관리하는 AuthenticationProvider에 정의되어있는 authenticate() 메소드를
            호출해서
         */
        //인증요청 ?
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
