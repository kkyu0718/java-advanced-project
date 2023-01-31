package com.kyuwon.spring.domain.user.repository;

import com.kyuwon.spring.domain.user.domain.UserAccount;
import com.kyuwon.spring.domain.user.model.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserAccount> findByAuthorityUsingQuerydsl(Authority authority);
    Page<UserAccount> searchPaging(Pageable pageable);
}
