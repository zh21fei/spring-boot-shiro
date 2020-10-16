package com.neo.sevice;

import com.neo.model.UserInfo;

import java.util.Optional;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/3
 */
public interface UserInfoService {
    // 通过username查找用户信息
    UserInfo findByUsername(String username);

    // 保存用户信息到数据库
    UserInfo save(UserInfo userInfo);

    // 根据id删除用户
    int deleteByUid(Integer uid);

    Optional<UserInfo> findById(int id);
}