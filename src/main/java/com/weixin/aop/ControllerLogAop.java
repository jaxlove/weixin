package com.weixin.aop;

import com.weixin.common.GsonTool;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wangdejun
 * @description: Controller层打印接收的参数日志
 * @date 2019/9/20 11:13
 */

@Aspect
@Component
public class ControllerLogAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*@PointCut注解表示表示横切点，哪些方法需要被横切*/
    @Pointcut(value = "@annotation(LogDesc)")
    public void logCut() {
    }


    @Before(value = "logCut()")
    public void logAop(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogDesc logDesc = method.getAnnotation(LogDesc.class);
        logger.info(logDesc.value() + " 接收参数为：{}", GsonTool.gsonString(args));
    }

}
