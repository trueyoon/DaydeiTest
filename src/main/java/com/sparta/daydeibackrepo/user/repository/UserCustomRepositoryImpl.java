package com.sparta.daydeibackrepo.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.daydeibackrepo.post.entity.ScopeEnum;
import com.sparta.daydeibackrepo.user.entity.CategoryEnum;
import com.sparta.daydeibackrepo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.sparta.daydeibackrepo.post.entity.QPost.post;
import static com.sparta.daydeibackrepo.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    //@Query("SELECT u FROM users u WHERE (u.email Like :searchWord "+" OR u.nickName Like :searchWord) "+" AND u !=:user")
    public List<User> findRecommmedList(String searchWord, User user1, List<CategoryEnum> categoryEnums) {
        BooleanExpression categoryExpression = null;
        if (categoryEnums != null && !categoryEnums.isEmpty()) {
            categoryExpression = user.categoryEnum.contains(categoryEnums.get(0));
            for (int i = 1; i < categoryEnums.size(); i++) {
                categoryExpression = categoryExpression.or(user.categoryEnum.contains(categoryEnums.get(i)));
            }
        }
        return jpaQueryFactory
                .selectFrom(user)
                .where(user.email.contains(searchWord).or(user.nickName.contains(searchWord))
                        .and(user.ne(user1)).and(categoryExpression))
                .fetch();
    }
    public List<User> findFamousList(User user1) {
        BooleanExpression categoryExpression = null;
        if (user1.getCategoryEnum() != null && !user1.getCategoryEnum().isEmpty()) {
            categoryExpression = user.categoryEnum.contains(user1.getCategoryEnum().get(0));}
            for (int i = 1; i < user1.getCategoryEnum().size(); i++) {
                categoryExpression = categoryExpression.or(user.categoryEnum.contains(user1.getCategoryEnum().get(i)));
            }
        return jpaQueryFactory
                .selectFrom(user)
                .where(user.ne(user1).and(categoryExpression))
                .fetch();
    }
    // 모든 유저 중에서 최근에 글을 올린(친구공개 제외) 유저를 List<User>로 뽑아옴
    public List<User> findAllUpdateUser(){
        return  jpaQueryFactory
                .select(post.user)
                .from(post)
                .where(post.modifiedAt.between(LocalDateTime.now().minus(7, ChronoUnit.DAYS), LocalDateTime.now())
                        .and(post.scope.in(ScopeEnum.ALL, ScopeEnum.SUBSCRIBE)))
                .orderBy(post.modifiedAt.desc())
                .distinct()
                .fetch();
    }
    // 모든 유저 중에서 최근에 글을 올린(친구공개 포함) 유저를 List<User>로 뽑아옴
    public List<User> findAllFriendUpdateUser(){
        return  jpaQueryFactory
                .select(post.user)
                .from(post)
                .where(post.modifiedAt.between(LocalDateTime.now().minus(7, ChronoUnit.DAYS), LocalDateTime.now())
                        .and(post.scope.in(ScopeEnum.ALL, ScopeEnum.SUBSCRIBE, ScopeEnum.FRIEND)))
                .orderBy(post.modifiedAt.desc())
                .distinct()
                .fetch();
    }
}
