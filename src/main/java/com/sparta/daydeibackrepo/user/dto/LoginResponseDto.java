package com.sparta.daydeibackrepo.user.dto;

import com.sparta.daydeibackrepo.user.entity.CategoryEnum;
import com.sparta.daydeibackrepo.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseDto {
    private String email;
    private String birthday;
    private String nickName;
    private String profileImage;
    private String introduction;
    private List<CategoryEnum> categoryList;
    private Boolean isLogin;

    public LoginResponseDto(User user, Boolean isLogin){
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.nickName = user.getNickName();
        this.profileImage = user.getProfileImage();
        this.introduction = user.getIntroduction();
        this.categoryList = user.getCategoryEnum();
        this.isLogin = isLogin;

    }
}
