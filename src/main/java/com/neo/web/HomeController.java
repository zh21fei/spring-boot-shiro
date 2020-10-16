package com.neo.web;

import com.neo.aop.annotation.OperLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/3
 */
@Controller
public class HomeController {
    @RequestMapping({"/", "/index"})
    public String index() {
        return "/index";
    }

    /*@RequestMapping(value = "/login")
    public void Login(String username, String password, Map<String, Object> map) {

        Serializable sessionId = null;
        String msg = null;
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                msg = "已登录";
            }

            Session session = subject.getSession();
            sessionId = session.getId();
            subject.login(token);

        } catch (UnknownAccountException u) {
            msg = "UnknownAccountException -- > 账户不存在：";
        } catch (IncorrectCredentialsException i) {
            msg = "IncorrectCredentialsException -- > 密码不正确：";
        } catch (AuthenticationException e) {
            msg = "认证失败";
        }
        map.put("msg", msg);
    }*/

    @RequestMapping("/login")
    @OperLog("用户登录")
    public void login(HttpServletRequest request, Map<String, Object> map){
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            msg = "已登录";
        }
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("------没有权限-------");
        return "403";
    }

}