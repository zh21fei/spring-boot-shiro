package com.neo.sevice;

import com.neo.model.SysRole;
import com.neo.model.UserInfo;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/4
 */
public interface SysRoleService {

    // 保存角色信息到数据库
    public abstract SysRole findByRole(String role);

    // 删除用户的时候删除角色信息
    void deleteById(Integer id);
}
