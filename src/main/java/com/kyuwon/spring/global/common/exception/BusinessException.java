package com.kyuwon.spring.global.common.exception;

import com.kyuwon.spring.global.common.api.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
}
