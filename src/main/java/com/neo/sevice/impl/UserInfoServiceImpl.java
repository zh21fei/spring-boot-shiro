package com.neo.sevice.impl;

import com.neo.dao.UserInfoDao;
import com.neo.model.UserInfo;
import com.neo.sevice.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/3
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    @Transactional  // 删除操作必须显示的声明事务
    @Override
    public int deleteByUid(Integer uid) {
        return userInfoDao.deleteByUid(uid);
    }

    @Override
    public Optional<UserInfo> findById(int id) {
        return userInfoDao.findById(id);
    }

}