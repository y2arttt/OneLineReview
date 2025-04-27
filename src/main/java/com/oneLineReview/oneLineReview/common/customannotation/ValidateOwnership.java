package com.oneLineReview.oneLineReview.common.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateOwnership {

    SERVICE service();

    enum SERVICE {
        REVIEW,
        COMMENT
    }
}
