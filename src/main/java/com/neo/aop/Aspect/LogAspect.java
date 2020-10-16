package com.neo.aop.Aspect;

import com.neo.aop.annotation.OperLog;
import com.neo.aop.dao.SysLogDao;
import com.neo.aop.model.SysLog;
import com.neo.aop.utils.HttpContextUtils;
import com.neo.aop.utils.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/16
 */


@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     * 表示匹配com.nec.wellness.controller包下的所有方法
     *
     */
    @Pointcut("execution(* com.neo.web.*.*(..))")
    public void pointcut(){};

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param point point
     * @return
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长
        long time = System.currentTimeMillis()-beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, Long time){
        // 切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        String id = UUID.randomUUID().toString().replace("-", "");
        sysLog.setId(id);
        OperLog operLogAnnotation = method.getAnnotation(OperLog.class);
        if (operLogAnnotation!=null){
            // 注解上的描述
            sysLog.setOperation(operLogAnnotation.value());
        }
        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = signature.getName();
        sysLog.setMethod(className+"."+methodName+"()");
        // 请求方法的参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = u.getParameterNames(method);
        if (args!=null&&parameterNames!=null){
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params+=" "+parameterNames[i]+":"+args[i];
            }
            sysLog.setParams(params);
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置Ip地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 设置操作者的用户名
        //sysLog.setUsername(getUserName(request));
        sysLog.setUrl(request.getRequestURL().toString());
        sysLog.setTime(Math.toIntExact(time));
        sysLog.setOperationTime(new Date());
        // 保存系统日志
        sysLogDao.save(sysLog);
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
