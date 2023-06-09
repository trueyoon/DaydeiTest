package com.sparta.daydeibackrepo.user.repository;

import com.sparta.daydeibackrepo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
        Optional<User> findByEmail(String email);

        Optional<User> findById(Long id);
        Optional<User> findByNickName(String nickName);

//        Optional<User> findByNickNameLikeAndEmailLike(String nickName, String Email);
        Optional<User> findByNickNameLike(String searchWord);
        Optional<User> findByEmailLike(String searchWord);
        Optional<User> findByKakaoId(Long id);

}
