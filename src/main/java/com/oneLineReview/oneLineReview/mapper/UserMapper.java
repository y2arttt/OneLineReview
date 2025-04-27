package com.oneLineReview.oneLineReview.mapper;

import com.oneLineReview.oneLineReview.Domain.Users;
import com.oneLineReview.oneLineReview.Dto.JoinDTO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


     Users getUserByEmail(String email);
     String getRoleById(Long userId);
     boolean insertUser(Users user);
}
