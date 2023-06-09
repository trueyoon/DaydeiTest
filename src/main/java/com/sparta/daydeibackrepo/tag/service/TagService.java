package com.sparta.daydeibackrepo.tag.service;

import com.sparta.daydeibackrepo.exception.CustomException;
import com.sparta.daydeibackrepo.friend.repository.FriendRepository;
import com.sparta.daydeibackrepo.friend.service.FriendService;
import com.sparta.daydeibackrepo.post.entity.Post;
import com.sparta.daydeibackrepo.post.entity.ScopeEnum;
import com.sparta.daydeibackrepo.post.repository.PostRepository;
import com.sparta.daydeibackrepo.security.UserDetailsImpl;
import com.sparta.daydeibackrepo.tag.dto.TagRequestDto;
import com.sparta.daydeibackrepo.tag.dto.TagResponseDto;
import com.sparta.daydeibackrepo.tag.entity.Tag;
import com.sparta.daydeibackrepo.user.dto.UserResponseDto;
import com.sparta.daydeibackrepo.user.entity.User;
import com.sparta.daydeibackrepo.user.repository.UserRepository;
import com.sparta.daydeibackrepo.userSubscribe.entity.UserSubscribe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.sparta.daydeibackrepo.exception.message.ExceptionMessage.UNAUTHORIZED_MEMBER;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final FriendService friendService;
    private final PostRepository postRepository;

    // 시작, 종료 일자 시간도 받아와야함.
    public List<TagResponseDto> getFriendTagList(TagRequestDto tagRequestDto, UserDetailsImpl userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new CustomException(UNAUTHORIZED_MEMBER)
        );
        List<TagResponseDto> tagResponseDtos = new ArrayList<>();
        LocalTime startTime = LocalTime.parse(tagRequestDto.getStartTime());
        LocalTime endTime = LocalTime.parse(tagRequestDto.getEndTime());
        LocalDate startDate = LocalDate.parse(tagRequestDto.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate;
        if (startTime.equals(LocalTime.parse("00:00")) && endTime.equals(LocalTime.parse("00:00"))){
            endDate = LocalDate.parse(tagRequestDto.getEndDate(), DateTimeFormatter.ISO_DATE).plusDays(1);
        }
        else {endDate = LocalDate.parse(tagRequestDto.getEndDate(), DateTimeFormatter.ISO_DATE);}
        List<Post> scheduledPosts = postRepository.findAll().stream()
                .filter(post -> (LocalDateTime.of(startDate,startTime).isAfter(LocalDateTime.of(post.getStartDate(),post.getStartTime()))
                        && LocalDateTime.of(startDate,startTime).isBefore(LocalDateTime.of(post.getEndDate(),post.getEndTime())))
                        || (LocalDateTime.of(endDate,endTime).isAfter(LocalDateTime.of(post.getStartDate(),post.getStartTime()))
                        && LocalDateTime.of(endDate,endTime).isBefore(LocalDateTime.of(post.getEndDate(),post.getEndTime())))
                        || ((LocalDateTime.of(startDate,startTime).isBefore(LocalDateTime.of(post.getStartDate(),post.getStartTime())) || LocalDateTime.of(startDate,startTime).isEqual(LocalDateTime.of(post.getStartDate(),post.getStartTime())))
                        && (LocalDateTime.of(endDate,endTime).isAfter(LocalDateTime.of(post.getEndDate(),post.getEndTime()))) || LocalDateTime.of(endDate,endTime).isEqual(LocalDateTime.of(post.getEndDate(),post.getEndTime())))
                && post.getScope() != ScopeEnum.ME)
                .toList();
        List<User> postUsers = scheduledPosts.stream()
                .map(post -> post.getUser())
                .collect(Collectors.toList());
        List<Tag> tags = new ArrayList<>();
        for(Post post : scheduledPosts){
            tags.addAll(post.getTag());
        }
        postUsers.addAll(tags.stream().map(tag -> tag.getUser()).toList());
        List<User> tagList = friendRepository.findTagUser(user, tagRequestDto.getSearchWord());
        for (User user1 : tagList){
            boolean scheduleCheck = false;
            if(postUsers.contains(user1))
            {scheduleCheck = true;}
            tagResponseDtos.add(new TagResponseDto(user1, scheduleCheck));
        }
        Collections.shuffle(tagResponseDtos);
        return tagResponseDtos;
    }
}
