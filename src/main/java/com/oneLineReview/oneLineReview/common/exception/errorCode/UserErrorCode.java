package com.oneLineReview.oneLineReview.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    //2 유저 오류
    UNLOGGED_USER("3022",HttpStatus.FOUND,""),
    UNAUTHORIZED_USER("4042",HttpStatus.NOT_FOUND,""),
    USERID_NOT_FOUND("5002", HttpStatus.BAD_REQUEST, "아이디나 비밀번호가 일치하지 않습니다.");

    private final String code;
    private final HttpStatus status;
    private final String reason;
}
