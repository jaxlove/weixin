package com.weixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wangdejun
 * @description: controller异常处理
 * @date 2019/11/19 11:43
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception ex) {
        logger.error("全局异常捕获：", ex);
        return "error";
    }

}
