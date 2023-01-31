package com.kyuwon.spring.domain.user.controller;

import com.kyuwon.spring.domain.user.domain.UserAccount;
import com.kyuwon.spring.domain.user.service.UserFindService;
import com.kyuwon.spring.global.common.api.ApiResponse;
import com.kyuwon.spring.global.common.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserFindService userFindService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserAccount>>> searchUsersUsingPaging(Pageable pageable) {
        Page<UserAccount> users = userFindService.searchPaging(pageable);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.PAGED_USERS_FOUND, users));
    }
}
