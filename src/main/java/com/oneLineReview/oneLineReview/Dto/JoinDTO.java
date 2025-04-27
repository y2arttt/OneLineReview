package com.oneLineReview.oneLineReview.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter
@NoArgsConstructor
public class JoinDTO{

    @NotNull
    @NotBlank(message = "닉네임은 공백일 수 없습니다.")
    private String nickname;

    @NotNull
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,15}$",
            message = "비밀번호는 6~15자의 영문 대소문자, 숫자, 특수문자(@$!%*?&)를 포함해야 합니다.")
    private String password;
}