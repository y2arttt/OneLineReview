package com.oneLineReview.oneLineReview.Domain;

import com.oneLineReview.oneLineReview.constant.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Users {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private UserRole role;
    private LocalDateTime created_at;

}
