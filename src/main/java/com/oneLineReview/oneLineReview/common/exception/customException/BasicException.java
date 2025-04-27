package com.oneLineReview.oneLineReview.common.exception.customException;

import com.oneLineReview.oneLineReview.common.exception.errorCode.BasicErrorCode;
import com.oneLineReview.oneLineReview.common.exception.errorCode.ErrorCode;
import lombok.Getter;

@Getter
public class BasicException extends RuntimeException {

    private final ErrorCode customExceptionType;

    public BasicException(ErrorCode customExceptionType) {
        super(customExceptionType.getReason());
        this.customExceptionType = customExceptionType;
    }

    public BasicException(ErrorCode customExceptionType, String message) {
        super(message);
        this.customExceptionType = customExceptionType;
    }

}
