package com.github.dreamroute.sqlrecorder.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.github.dreamroute.sqlrecorder.domain.User;

public class UserMapperTest {
    
    @Test
    public void insertUserTest() throws Exception {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("conf/config.xml"));
        SqlSession sqlSession = factory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setPassword("test");
        userMapper.insertUser(user);
    }

}
