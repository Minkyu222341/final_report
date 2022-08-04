package com.sparta.week3_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week32Application {

    public static void main(String[] args) {
        SpringApplication.run(Week32Application.class, args);
    }

}
