package com.sparta.week3_2.service;

import com.sparta.week3_2.domain.User.SignupRequestDto;
import com.sparta.week3_2.domain.User.User;
import com.sparta.week3_2.domain.comment.Comment;
import com.sparta.week3_2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";



    public String registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            return new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.").getMessage();
        }
            // 패스워드 암호화
            String password = passwordEncoder.encode(requestDto.getPassword());
            String email = requestDto.getEmail();

            User user = new User(username, password, email);
            userRepository.save(user);
        return "회원가입완료";
    }

    public List<Comment> getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("오류"));
        List<Comment> comment = user.getComment();
        return comment;
    }
}