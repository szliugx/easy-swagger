package com.github.szliugx.easyswagger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({EasySwaggerConfiguration.class})
public @interface EnableEasySwagger {
}
