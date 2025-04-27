package com.oneLineReview.oneLineReview.common.exception.customException;

import com.oneLineReview.oneLineReview.common.exception.errorCode.ErrorCode;

public class ReviewException extends BasicException{
    public ReviewException(ErrorCode customExceptionType) {
        super(customExceptionType);
    }

    public ReviewException(ErrorCode customExceptionType, String message) {
        super(customExceptionType, message);
    }

    @Override
    public ErrorCode getCustomExceptionType() {
        return super.getCustomExceptionType();
    }
}
