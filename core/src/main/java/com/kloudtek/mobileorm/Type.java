package com.kloudtek.mobileorm;

/**
 * Created by yannick on 9/18/15.
 */
public @interface Type {
    String value();
    String persister() default "";
}
