package cn.unminded.rtool.boot.annotation;

import org.springframework.stereotype.Component;

/**
 * 规则注解 定义某个class是一个规则组件
 */
@Component
public @interface Rule {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any (or empty String otherwise)
     */
    String value() default "";

}
