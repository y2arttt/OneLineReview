package com.oneLineReview.oneLineReview.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {
    //3 = 리뷰 오류
    DUPLICATE_RECOMMEND("4003", HttpStatus.BAD_REQUEST, "이미 추천한 리뷰입니다."),
    DUPLICATE_REVIEW("40031", HttpStatus.BAD_REQUEST, "이미 작성한 리뷰입니다.");
    private final String code;
    private final HttpStatus status;
    private final String reason;
}
