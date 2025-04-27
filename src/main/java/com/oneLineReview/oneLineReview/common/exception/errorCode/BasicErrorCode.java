package com.oneLineReview.oneLineReview.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BasicErrorCode implements ErrorCode{

    // 1 = 범용 오류
    BAD_REQUEST("4001", HttpStatus.BAD_REQUEST, "요청이 잘못되었습니다."),
    UNAUTHORIZED("4011", HttpStatus.UNAUTHORIZED, "잘못된 요청입니다."),
    FORBIDDEN("4031", HttpStatus.FORBIDDEN, "권한 없음"),
    NOT_FOUND("4041", HttpStatus.NOT_FOUND, "찾을 수 없음"),
    METHOD_NOT_ALLOWED("4051", HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드"),
    UNKNOWN("5001", HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버 에러가 발생하였습니다.");


    private final String code;
    private final HttpStatus status;
    private final String reason;

}
