package com.weixin.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface LogDesc {
    String value();
}
