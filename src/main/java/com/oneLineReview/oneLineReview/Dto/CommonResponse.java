package com.oneLineReview.oneLineReview.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponse<T> {
    private boolean success;
    private T result;
}
