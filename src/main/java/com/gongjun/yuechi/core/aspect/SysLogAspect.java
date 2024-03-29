package com.gongjun.yuechi.core.aspect;

import com.alibaba.fastjson.JSON;
import com.gongjun.yuechi.core.annotation.MyLog;
import com.gongjun.yuechi.core.utils.IPDeal;
import com.gongjun.yuechi.core.utils.JWTUtil;
import com.gongjun.yuechi.model.log.SysLog;
import com.gongjun.yuechi.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Author:GongJun
 * Date:2019/1/17
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private ISysLogService service;

    /**
     * @param
     * @return void
     * @description 定义切点 @Pointcut,在注解的位置切入代码
     * @author GongJun
     * @time 2019/1/17 14:59
     **/
    @Pointcut("@annotation(com.gongjun.yuechi.core.annotation.MyLog)")
    public void logPointCut() {

    }

    /**
     * @param
     * @return void
     * @description
     * @author GongJun
     * @time 2019/1/17 15:25
     **/
    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {

        System.out.println("***日志开始***");
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);

        if (myLog != null) {
            String value = myLog.value();
            sysLog.setDescription(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParameter(params);
        sysLog.setStartTime(new Date().getTime());


        //获取用户ip地址
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        sysLog.setUri(request.getRequestURI());
        sysLog.setUrl(request.getRequestURL().toString());
        sysLog.setBasePath(request.getServletPath());
        sysLog.setUserAgent(request.getHeader("User-Agent"));
        sysLog.setIp(IPDeal.getIpAddress(request));
        //获取用户名
        try {
            sysLog.setUsername(JWTUtil.getUsername());
        } catch (Exception e) {
            sysLog.setUsername(request.getParameter("username"));
        }
        //调用service保存SysLog实体类到数据库
        this.service.insert(sysLog);
        System.out.println("***日志结束***");
    }
}
