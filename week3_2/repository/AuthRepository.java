package com.sparta.week3_2.repository;

import com.sparta.week3_2.domain.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<RefreshToken,Long> {
}
