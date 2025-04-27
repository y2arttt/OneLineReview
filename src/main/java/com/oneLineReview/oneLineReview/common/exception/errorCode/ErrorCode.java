package com.oneLineReview.oneLineReview.common.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getCode();
    HttpStatus getStatus();
    String getReason();
}
