package com.kyuwon.spring.service;

import com.kyuwon.spring.domain.UserAccount;
import com.kyuwon.spring.dto.response.SignUpResponse;
import com.kyuwon.spring.exception.BusinessException;
import com.kyuwon.spring.exception.ErrorCode;
import com.kyuwon.spring.model.Address;
import com.kyuwon.spring.model.Authority;
import com.kyuwon.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponse saveUser(
            String name,
            String email,
            String password,
            String country,
            String state,
            String city,
            String zipCode
    ) {
        Address address = Address.builder()
                .country(country)
                .state(state)
                .city(city)
                .zipCode(zipCode)
                .build();

        UserAccount userAccount = UserAccount.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .authorities(Set.of(Authority.USER))
                .address(address)
                .build();

        validateDuplicateEmail(email);
        UserAccount user = userRepository.save(userAccount);
        return SignUpResponse.of(user);
    }

    private void validateDuplicateEmail(String email) {
        Optional<UserAccount> user = userRepository.findByEmail(email);
        if(!user.isEmpty()) {
            throw new BusinessException(ErrorCode.DUPLICATED_EMAIL);
        }
    }
}
