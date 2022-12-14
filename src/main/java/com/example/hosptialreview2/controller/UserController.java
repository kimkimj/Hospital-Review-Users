package com.example.hosptialreview2.controller;

import com.example.hosptialreview2.domain.Response;
import com.example.hosptialreview2.domain.dto.UserDto;
import com.example.hosptialreview2.domain.dto.UserJoinRequest;
import com.example.hosptialreview2.domain.dto.UserJoinResponse;
import com.example.hosptialreview2.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest){
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(), userDto.getEmail()));
    }
}