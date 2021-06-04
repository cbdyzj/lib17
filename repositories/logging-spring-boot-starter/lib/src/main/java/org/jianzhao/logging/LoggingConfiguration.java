package org.jianzhao.logging;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    @Bean
    public DefaultPointcutAdvisor loggingAspect(LoggingInterceptor loggingInterceptor) {
        // advisor
        var advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(AnnotationMatchingPointcut.forMethodAnnotation(Logging.class));
        advisor.setAdvice(loggingInterceptor);
        return advisor;
    }

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }
}
