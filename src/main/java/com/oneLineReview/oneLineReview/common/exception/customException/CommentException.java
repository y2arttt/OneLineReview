package com.oneLineReview.oneLineReview.common.exception.customException;

import com.oneLineReview.oneLineReview.common.exception.errorCode.ErrorCode;

public class CommentException extends BasicException{


    public CommentException(ErrorCode customExceptionType) {
        super(customExceptionType);
    }

    public CommentException(ErrorCode customExceptionType, String message) {
        super(customExceptionType, message);
    }
}
