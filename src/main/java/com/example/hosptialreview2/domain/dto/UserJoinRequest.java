package com.example.hosptialreview2.domain.dto;

import com.example.hosptialreview2.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {
    private String username;
    private String password;
    private String email;

    public User toEntity(String password) {
        return User.builder()
                .username(this.username)
                .password(password)
                .emailAddress(this.email)
                .build();
    }
}
