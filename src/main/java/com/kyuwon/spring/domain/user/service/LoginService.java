package com.kyuwon.spring.domain.user.service;

import com.kyuwon.spring.domain.user.domain.UserAccount;
import com.kyuwon.spring.domain.user.dto.response.LoginResponse;
import com.kyuwon.spring.domain.user.repository.UserRepository;
import com.kyuwon.spring.global.common.error.exception.BusinessException;
import com.kyuwon.spring.global.common.error.exception.ErrorCode;
import com.kyuwon.spring.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class LoginService {
    private final UserFindService userFindService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public LoginResponse loginUser(String email, String password) {
        UserAccount user = userFindService.findByEmail(email);
        checkPassword(password, user.getPassword());

        String accessToken = jwtTokenProvider.createAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        // refresh token이 이미 존재하면 새로 갱신하고, 없어도 다시 발급한다.
        saveRefreshToken(user, refreshToken);

        return LoginResponse.of(accessToken, refreshToken);
    }

    private void checkPassword(String loginPassword, String userPassword) {
        if(!passwordEncoder.matches(loginPassword, userPassword)) {
            throw new BusinessException(ErrorCode.LOGIN_INPUT_INVALID);
        }
    }

    @Transactional
    public void saveRefreshToken(UserAccount user, String refreshToken) {
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }
}
