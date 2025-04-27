package com.oneLineReview.oneLineReview.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookErrorCode implements ErrorCode {
    //4  책 오류
    BOOK_NOT_FOUND_DB("4044",HttpStatus.NOT_FOUND, "책을 찾을 수 없습니다.");
    private final String code;
    private final HttpStatus status;
    private final String reason;

}
