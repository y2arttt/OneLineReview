package com.oneLineReview.oneLineReview.users;


import com.oneLineReview.oneLineReview.Domain.Users;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class UsersDao {

//    @Autowired
    private SqlSessionTemplate sqlSession;
    private String namespace = "userMapper";

    //아이디로 검색
    public Users getUserByEmail(String email) {
        log.info("getUserByEmail={}", email);
        return sqlSession.selectOne(namespace+".getUserByEmail", email);
    }


    //삽입
    public boolean insertUser(Users usersVo) {
//        joinDTO.setCreated_at(LocalDateTime.now());
        int query = sqlSession.insert(namespace + ".insert", usersVo);
        if (query > 0) {
            return true;
        }
        else return false;
    }

}
