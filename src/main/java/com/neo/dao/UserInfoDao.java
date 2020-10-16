package com.neo.dao;

import com.neo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/3
 */
public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    // 通过username查找用户信息
    UserInfo findByUsername(String username);

    // 根据id删除用户
    int deleteByUid(Integer uid);

}