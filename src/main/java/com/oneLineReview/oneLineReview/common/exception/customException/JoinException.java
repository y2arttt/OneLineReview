package com.oneLineReview.oneLineReview.common.exception.customException;

import com.oneLineReview.oneLineReview.common.exception.errorCode.BasicErrorCode;
import lombok.Getter;

@Getter
public class JoinException extends BasicException {

    public JoinException(BasicErrorCode errorCode) {
        super(errorCode, errorCode.getReason());
    }

    public JoinException(BasicErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}
