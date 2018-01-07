package com.zhengyun.mvc.annotation;

/**
 * Created by 听风 on 2017/12/30.
 */
public @interface RequestMapping {

    public static String GET = "get";

    public static String POST = "post";

    String value() default "";

    String method() default GET;

}
