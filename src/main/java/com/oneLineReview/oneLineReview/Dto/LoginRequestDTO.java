package com.oneLineReview.oneLineReview.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LoginRequestDTO {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
