package com.oneLineReview.oneLineReview.common.exception.customException;

import com.oneLineReview.oneLineReview.common.exception.errorCode.ErrorCode;

public class NaverAPIException extends BasicException{

    public NaverAPIException(ErrorCode customExceptionType) {
        super(customExceptionType);
    }
}
