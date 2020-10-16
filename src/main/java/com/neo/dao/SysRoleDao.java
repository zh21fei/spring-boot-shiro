package com.neo.dao;

import com.neo.model.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/4
 */
public interface SysRoleDao extends JpaRepository<SysRole,Integer> {
    SysRole findByRole(String role);

    @Override
    void deleteById(Integer id);
}
