package com.example.datawarehouse;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseWrapper {
    String message() default "Payload Successful";
}
