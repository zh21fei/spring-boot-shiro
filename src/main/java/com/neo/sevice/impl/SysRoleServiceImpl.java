package com.neo.sevice.impl;

import com.neo.dao.SysRoleDao;
import com.neo.model.SysRole;
import com.neo.sevice.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/4
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public SysRole findByRole(String role) {
        return sysRoleDao.findByRole(role);
    }

    @Override
    public void deleteById(Integer id) {
        sysRoleDao.deleteById(id);
    }
}
