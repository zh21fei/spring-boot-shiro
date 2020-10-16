package com.neo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/3
 */

@Entity
@Getter
@Setter
public class SysRole {
    @Id
    private Integer id;
    // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String role;
    // 角色描述,UI界面显示使用
    private String description;

    // 用户 - 角色关系定义;
    //@ManyToMany
    //@JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
    @ManyToMany(mappedBy = "roleList")  //配置多表关系
    private List<UserInfo> userInfos;// 一个角色对应多个用户

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}