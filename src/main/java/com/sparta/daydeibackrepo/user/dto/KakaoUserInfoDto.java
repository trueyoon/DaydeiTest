package com.sparta.daydeibackrepo.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String email;
    private String nickName;
    private String img;
    private String birthday;
//    private List<String> friendEmailList;

//    public KakaoUserInfoDto(Long id, String nickName, String email) {
//        this.id = id;
//        this.nickName = nickName;
//        this.email = email;
//    }
                                                                                                    //, List<String> friendEmailList
    public KakaoUserInfoDto(Long id, String nickName, String email, String img, String birthday) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.img = img;
        this.birthday = birthday;
//        this.friendEmailList = friendEmailList;
    }
}