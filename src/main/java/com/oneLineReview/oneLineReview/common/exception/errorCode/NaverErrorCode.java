package com.oneLineReview.oneLineReview.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum NaverErrorCode implements ErrorCode {
    //6네이버 오류
    EMPTY_KEYWORD("4046",HttpStatus.BAD_REQUEST, "키워드 존재하지 않음");

    private final String code;
    private final HttpStatus status;
    private final String reason;

}
