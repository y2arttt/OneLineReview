package com.oneLineReview.oneLineReview.common.exception.customException;
import com.oneLineReview.oneLineReview.common.exception.errorCode.ErrorCode;
import lombok.Getter;

@Getter
public class BookException extends BasicException {

    private final String isbn;

    public BookException(ErrorCode errorCode) {
        super(errorCode);
        this.isbn = "";
    }

    public BookException(ErrorCode errorCode, String isbn) {
        super(errorCode,"[도서 번호: "+isbn+"]" + errorCode.getReason());
        this.isbn = isbn;
    }
}
