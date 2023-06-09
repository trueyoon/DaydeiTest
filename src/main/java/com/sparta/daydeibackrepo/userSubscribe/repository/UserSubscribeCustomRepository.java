package com.sparta.daydeibackrepo.userSubscribe.repository;

import com.sparta.daydeibackrepo.user.entity.User;
import com.sparta.daydeibackrepo.userSubscribe.entity.UserSubscribe;
import com.sparta.daydeibackrepo.util.SortEnum;

import java.util.List;

public interface UserSubscribeCustomRepository {
    List<User> findAllSubscriberUser(User user);
    List<User> findAllSubscribingUserBySort(User user, SortEnum sortEnum);

    List<User> findAllSubscriberUserBySort(User user, SortEnum sortEnum);
    List<User> findVisibleUserSubscribe(User user);
}
