package com.sparta.week3_2.domain.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    @Column
    private Date expireRefreshToken;

    public RefreshToken(String accessToken, String refreshToken, Date expireRefreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireRefreshToken = expireRefreshToken;
    }
}
