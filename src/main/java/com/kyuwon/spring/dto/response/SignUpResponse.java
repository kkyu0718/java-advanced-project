package com.kyuwon.spring.dto.response;

import com.kyuwon.spring.domain.UserAccount;
import com.kyuwon.spring.model.Address;

public record SignUpResponse(
        String email,
        String password,
        String name,
        Address address
) {
    public static SignUpResponse of (UserAccount entity) {
        return new SignUpResponse(
                entity.getEmail(),
                entity.getPassword(),
                entity.getName(),
                entity.getAddress()
        );
    }
}
