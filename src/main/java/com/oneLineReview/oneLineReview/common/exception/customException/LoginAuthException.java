package com.oneLineReview.oneLineReview.common.exception.customException;

import com.oneLineReview.oneLineReview.common.exception.errorCode.BasicErrorCode;
import com.oneLineReview.oneLineReview.common.exception.errorCode.ErrorCode;

public class LoginAuthException extends BasicException{

    public LoginAuthException(ErrorCode errorCode) {
        super(errorCode, errorCode.getReason());
    }

    public LoginAuthException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
