package com.oneLineReview.oneLineReview.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {
    //5 댓글 오류
    COMMENT_NOT_FOUND_DB("4045", HttpStatus.NOT_FOUND,"존재하지 않는 댓글입니다.");
    private final String code;
    private final HttpStatus status;
    private final String reason;
}
