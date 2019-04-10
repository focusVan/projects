package com.focus.spring.dao;

import com.focus.spring.entity.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * focus Create in 15:08 2019/3/29
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO .....")
    int insert(UserDO userDO);
}
