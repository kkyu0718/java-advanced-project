package com.kyuwon.spring.domain.user.repository;

import com.kyuwon.spring.domain.user.domain.QUserAccount;
import com.kyuwon.spring.domain.user.domain.UserAccount;
import com.kyuwon.spring.domain.user.model.Authority;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    QUserAccount user = QUserAccount.userAccount;

    @Override
    public List<UserAccount> findByAuthorityUsingQuerydsl(Authority authority) {

        return queryFactory.selectFrom(user)
                .where(
                        user.authority.contains(authority)
                )
                .fetch();
    }

    @Override
    public Page<UserAccount> searchPaging(Pageable pageable) {
        List<UserAccount> users = queryFactory
                .selectFrom(user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(users, pageable, users.size());
    }
}
