package com.oneLineReview.oneLineReview.service;

import com.oneLineReview.oneLineReview.Dto.JoinDTO;
import com.oneLineReview.oneLineReview.Dto.LoginRequestDTO;
import com.oneLineReview.oneLineReview.common.exception.errorCode.BasicErrorCode;
import com.oneLineReview.oneLineReview.common.exception.customException.JoinException;
import com.oneLineReview.oneLineReview.common.exception.customException.LoginAuthException;

import com.oneLineReview.oneLineReview.common.exception.errorCode.UserErrorCode;
import com.oneLineReview.oneLineReview.constant.UserRole;
import com.oneLineReview.oneLineReview.mapper.UserMapper;
import com.oneLineReview.oneLineReview.users.UsersDao;
import com.oneLineReview.oneLineReview.Domain.Users;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

//    private final UsersDao usersDao;
    private final UserMapper userMapper;


    public boolean register(JoinDTO joinDTO){

        String email = joinDTO.getEmail();
        if (userMapper.getUserByEmail(email) != null){
            throw new JoinException(BasicErrorCode.BAD_REQUEST,"이미 존재하는 아이디 입니다.");
        }

        Users user = Users.builder()
                .email(email)
                .password(joinDTO.getPassword())
                .nickname(joinDTO.getNickname())
                .role(UserRole.ROLE_USER)
                .created_at(LocalDateTime.now())
                .build();

        return userMapper.insertUser(user);

    }

    // 로그인
    public Long login(LoginRequestDTO loginRequest)   {
        Users getUserByEmail = userMapper.getUserByEmail(loginRequest.getEmail());

        if (getUserByEmail == null) {
            throw new LoginAuthException(UserErrorCode.USERID_NOT_FOUND);
        }

        log.info("getUserByEmail: " + getUserByEmail.getPassword());
        log.info("loginRequest: " + loginRequest.getPassword());

        if(getUserByEmail.getPassword().equals(loginRequest.getPassword())){
            return getUserByEmail.getId();
        }
        throw new LoginAuthException(UserErrorCode.USERID_NOT_FOUND);

    }

    public String roleCheck(String id) {
        return userMapper.getRoleById(Long.valueOf(id));

    }

}
