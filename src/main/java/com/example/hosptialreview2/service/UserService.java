package com.example.hosptialreview2.service;

import com.example.hosptialreview2.domain.User;
import com.example.hosptialreview2.domain.dto.UserDto;
import com.example.hosptialreview2.domain.dto.UserJoinRequest;
import com.example.hosptialreview2.exception.ErrorCode;
import com.example.hosptialreview2.exception.HospitalReviewAppException;
import com.example.hosptialreview2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto join(UserJoinRequest request){
        // 비즈니스 로직: 회원가입
        // usernmae 중복 체크
        // 중복이면 회원가입 x --> Exception 처리

        /*
            Error: 이건 해당 username을 가진 user가 있을 때 에러 처리하는 코드
            userRepository.findByUsername(request.getUsername())
                        .orElseThrow(() -> new RuntimeException("해당 usernmae이 중복됩니다"));
         */

        // usernmae을 가지 user가 있으면 에러 처리
        userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USERNAME, String.format("UserName:%s", request.getUsername()));
        });

        // 회원가입 .save()
        // userJoinReqest로 받았으르모 entity 로 받아줘야함
        User savedUser = userRepository.save(request.toEntity());
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUsername())
                .email(savedUser.getEmailAddress())
                .build();
    }
}