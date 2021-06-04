package org.jianzhao.logging;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LoggingConfiguration.class)
@Documented
public @interface EnableLogging {
}
