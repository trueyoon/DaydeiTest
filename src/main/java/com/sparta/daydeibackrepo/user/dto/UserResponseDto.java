package com.sparta.daydeibackrepo.user.dto;

import com.sparta.daydeibackrepo.user.entity.CategoryEnum;
import com.sparta.daydeibackrepo.user.entity.User;
import com.sparta.daydeibackrepo.user.repository.UserRepository;
import com.sparta.daydeibackrepo.userSubscribe.entity.UserSubscribe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String nickName;
    private String profileImage;
    private String introduction;
    private String birthday;
    private List<CategoryEnum> categoryList;
    private Boolean friendCheck;
    private Boolean isRequestFriend;
    private Boolean userSubscribeCheck;
    private int friendCount;
    private int subscribingCount;
    private int subscriberCount;
    public UserResponseDto(User user, boolean friendCheck,boolean isRequestFriend, boolean userSubscribeCheck){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.profileImage = user.getProfileImage();
        this.introduction = user.getIntroduction();
        this.categoryList = user.getCategoryEnum();
        this.birthday = user.getBirthday();
        this.friendCheck = friendCheck;
        this.isRequestFriend = isRequestFriend;
        this.userSubscribeCheck = userSubscribeCheck;
        this.friendCount = user.getFriendCount();
        this.subscribingCount = user.getSubscribing().size();
        this.subscriberCount = user.getSubscriber().size();
    }
    public UserResponseDto(User user, boolean friendCheck, boolean userSubscribeCheck){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.profileImage = user.getProfileImage();
        this.introduction = user.getIntroduction();
        this.categoryList = user.getCategoryEnum();
        this.birthday = user.getBirthday();
        this.friendCheck = friendCheck;
        this.userSubscribeCheck = userSubscribeCheck;
        this.friendCount = user.getFriendCount();
        this.subscribingCount = user.getSubscribing().size();
        this.subscriberCount = user.getSubscriber().size();
    }
    public UserResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.profileImage = user.getProfileImage();
        this.introduction = user.getIntroduction();
        this.categoryList = user.getCategoryEnum();
        this.birthday = user.getBirthday();
        this.friendCount = user.getFriendCount();
        this.subscribingCount = user.getSubscribing().size();
        this.subscriberCount = user.getSubscriber().size();
    }
}
