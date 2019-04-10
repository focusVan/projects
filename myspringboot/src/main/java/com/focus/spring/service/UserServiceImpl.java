package com.focus.spring.service;

import com.focus.spring.dao.UserMapper;
import com.focus.spring.dto.UserDTO;
import com.focus.spring.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * focus Create in 15:13 2019/3/29
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public int add(UserDTO userDTO) {
        return userMapper.insert(new UserDO());
    }
}
