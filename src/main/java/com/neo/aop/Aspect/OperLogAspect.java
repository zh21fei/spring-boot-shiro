package com.neo.aop.Aspect;/*
package com.nec.wellness.aop.Aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.nec.wellness.aop.annotation.OperLog;
import com.nec.wellness.aop.dao.SysLogDao;
import com.nec.wellness.aop.model.SysLog;
import com.nec.wellness.aop.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
*/
/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/16
 *//*

@Aspect
@Component
@Slf4j
public class OperLogAspect {

    @Autowired
    private SysLogDao sysLogDao;

    */
/**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     * 表示匹配com.nec.wellness.controller包下的所有方法
     *
     *//*

    @Pointcut("execution(* com.nec.wellness.controller.*.*(..))")
    public void operLogPoinCut() {
    }

    */
/**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     *//*

    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        long beginTime = System.currentTimeMillis();
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        SysLog sysLog = new SysLog();
        String id = UUID.randomUUID().toString().replace("-", "");
        sysLog.setId(id);
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperLog opLog = method.getAnnotation(OperLog.class);
            if (opLog != null) {
                String value = opLog.value();
                sysLog.setOperation(value);
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName + "()";

            // 请求方法
            sysLog.setMethod(methodName);
            String params = getParams(joinPoint, method);

            sysLog.setParams(params);
            // 请求用户名称
            String username = getUserName(request);

            sysLog.setUsername(username);
            sysLog.setUrl(request.getRequestURL().toString());
            // 请求IP
            sysLog.setIp(IPUtils.getIpAddr(request));
            // 创建时间
            sysLog.setOperationTime(new Date());
            sysLog.setTime((int) (System.currentTimeMillis() - beginTime));
            // 保存日志
            sysLogDao.save(sysLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取请求的参数名称
    private String getParams(JoinPoint joinPoint, Method method) {
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        String params = null;
        if (args != null && paramNames != null) {
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
        }
        return params;
    }

    // 获取当前操作的用户名称
    private String getUserName(HttpServletRequest request) {
        String name = request.getUserPrincipal().getName();
        String substring = name.substring(5, name.length() - 3);
        String[] split = substring.split(",");
        String username = null;
        for (int i = 0; i <split.length ; i++) {
            String s = split[3];
            username = s.substring(7, s.length() - 1);
        }
        return username;
    }
}
*/
