package com.sparta.daydeibackrepo.friend.controller;

import com.sparta.daydeibackrepo.friend.dto.FriendListResponseDto;
import com.sparta.daydeibackrepo.friend.dto.FriendResponseDto;
import com.sparta.daydeibackrepo.friend.dto.RelationResponseDto;
import com.sparta.daydeibackrepo.friend.service.FriendService;
import com.sparta.daydeibackrepo.security.UserDetailsImpl;
import com.sparta.daydeibackrepo.user.dto.UserResponseDto;
import com.sparta.daydeibackrepo.util.StatusResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import jdk.jshell.Snippet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendController {
    private final FriendService friendService;
    @PostMapping("/{userId}")
    public StatusResponseDto<FriendResponseDto> requestFriend(@PathVariable Long userId, @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return StatusResponseDto.success(friendService.requestFriend(userId, userDetails));
    }
    @PutMapping("/{userId}")
    public StatusResponseDto<FriendResponseDto> setFriend(@PathVariable Long userId, @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return StatusResponseDto.success(friendService.setFriend(userId, userDetails));
    }
    @DeleteMapping("/{userId}")
    public StatusResponseDto<String> deleteFriend(@PathVariable Long userId, @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return StatusResponseDto.success(friendService.deleteFriend(userId, userDetails));
    }
    @GetMapping("/list")
    public StatusResponseDto<RelationResponseDto> getRelationList(@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return StatusResponseDto.success(friendService.getRelationList(userDetails));
    }
    @GetMapping("/list/{userId}")
    public StatusResponseDto<RelationResponseDto> getYourRelationList(@PathVariable Long userId, @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return StatusResponseDto.success(friendService.getYourRelationList(userId, userDetails));
    }
    @GetMapping("/recommend")
    public StatusResponseDto<List<UserResponseDto>> getRecommendList(@RequestParam List<String> category, @RequestParam String searchword, @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return StatusResponseDto.success(friendService.getRecommendList(category,searchword,userDetails));
    }

    // 친구 불러오기 (왼쪽 사이드바)
    @GetMapping("/update")
    public StatusResponseDto<?> getUpdateFriend(@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return StatusResponseDto.success(friendService.getUpdateFriend(userDetails));
    }

    @GetMapping("/list/famous")
    public StatusResponseDto<List<UserResponseDto>> getFamousList(@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return StatusResponseDto.success(friendService.getFamousList(userDetails));
    }

    @GetMapping("/list/response")
    public StatusResponseDto<List<UserResponseDto>> getPendingResponseList(@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return StatusResponseDto.success(friendService.getPendingResponseList(userDetails));
    }
}
