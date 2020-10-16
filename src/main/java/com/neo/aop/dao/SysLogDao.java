package com.neo.aop.dao;

import com.neo.aop.model.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/16
 */
public interface SysLogDao extends JpaRepository<SysLog,Integer> {
}
