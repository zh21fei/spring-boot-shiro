package com.neo.aop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/16
 */

@Setter
@Getter
@Entity
@Table(name = "sys_log")
public class SysLog {

    @Id
    private String id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date operationTime;
    private String url;
}
