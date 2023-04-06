package com.sparta.daydeibackrepo.web.controller;


import com.sparta.daydeibackrepo.DaydeiBackRepoApplication;
import com.sparta.daydeibackrepo.util.StatusResponseDto;
import lombok.AllArgsConstructor;

import org.apache.http.HttpStatus;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@AllArgsConstructor
public class WebRestController {

    private Environment env;

    @GetMapping("/profile")
    public StatusResponseDto<String> getProfile(){
//        return Arrays.stream(env.getActiveProfiles())
//                .findFirst()
//                .orElse("");
        return StatusResponseDto.success("success");
    }
}
