package com.neo.web;

import com.neo.aop.annotation.OperLog;
import com.neo.model.SysRole;
import com.neo.model.UserInfo;
import com.neo.sevice.SysRoleService;
import com.neo.sevice.UserInfoService;
import com.neo.utils.PassWordUtils;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/3
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 根据用户名称查询用户信息.
     *
     * @param
     * @return
     */
    @OperLog("根据用户名称查询用户信息")
    @RequestMapping("/userList/{username}")
    @RequiresRoles({"admin"})
    public String userInfo(@PathVariable(value = "username") String username) {
        UserInfo admin = userInfoService.findByUsername(username);
        System.out.println(admin.toString());
        return "userInfo";
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param id
     * @return
     */
    @OperLog("根据用户ID查询用户信息")
    @GetMapping(value = "/userList/{id}")
    @RequiresRoles(value = {"admin", "test"}, logical = Logical.OR)
    public String getUserById(@PathVariable(value = "id") int id) {
        Optional<UserInfo> userInfo = userInfoService.findById(id);
        System.out.println(userInfo.toString());
        return "userInfo";
    }

    /**
     * 添加用户 新建用户
     * logical = Logical.OR 满足其中一个角色即可访问。默认值是Logical.AND,必须同时满足才可访问
     * @param
     * @return
     */
    @GetMapping("/userAdd/{uid}")
    @RequiresRoles(value = {"admin", "test"}, logical = Logical.OR)
    public String userInfoAdd(@PathVariable(value = "uid") int uid) {

        UserInfo userInfo = new UserInfo();
        userInfo.setName("会员");
        userInfo.setUsername("vip");
        userInfo.setUid(uid);
        userInfo.setSalt(PassWordUtils.getRandomString());
        userInfo.setPassword(PassWordUtils.md5("123456", userInfo.getUsername() + userInfo.getSalt()));

        // 用户可能有多个角色：list
        ArrayList<String> arrayList = new ArrayList<>(16);
        arrayList.add("admin");
        arrayList.add("test");
        List<SysRole> sysRoles = getSysRoles(arrayList);

        // 设置角色信息
        userInfo.setRoleList(sysRoles);
        userInfoService.save(userInfo);
        System.out.println("添加用户成功" + userInfo.toString());
        return "userInfoAdd";
    }

    /**
     * 根据角色名称集合获取角色信息
     *
     * @param roles
     * @return
     */
    private List<SysRole> getSysRoles(List<String> roles) {
        ArrayList<SysRole> list = new ArrayList<>();
        for (String role : roles) {
            SysRole sysRole = new SysRole();
            SysRole byRole = sysRoleService.findByRole(role);
            sysRole.setId(byRole.getId());
            sysRole.setRole(byRole.getRole());
            sysRole.setDescription(byRole.getDescription());
            list.add(sysRole);
        }
        return list;
    }

    /**
     * 用户更新
     *
     * @param uid 用户ID
     * @return
     */
    @RequestMapping("/userUp")
    @RequiresRoles({"admin"})
    public String userUpdate(Integer uid) {

        return "userUpdate";
    }

    /**
     * 删除用户
     *
     * @param uid 用户ID
     * @return
     */
    @GetMapping("/userDel/{uid}")
    @RequiresRoles({"admin"})
    public String userDel(@PathVariable(value = "uid") Integer uid) {
        userInfoService.deleteByUid(uid);
        return "userInfoDel";
    }
}