package org.jianzhao.boot.configuration;

import org.jianzhao.boot.support.cache.CacheInterceptor;
import org.jianzhao.boot.support.cache.CacheStrategy;
import org.jianzhao.boot.support.cache.ContextCacheStrategy;
import org.jianzhao.boot.support.cache.LocalCacheStrategy;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CacheConfiguration {

    /**
     * 上下文缓存策略
     */
    @Bean
    public CacheStrategy contextCacheStrategy() {
        return new ContextCacheStrategy();
    }

    /**
     * 本地缓存策略
     */
    @Bean
    public CacheStrategy localCacheStrategy() {
        return new LocalCacheStrategy();
    }

    /**
     * 缓存切面
     */
    @Bean
    public DefaultPointcutAdvisor cachePointcutAdvisor(List<CacheStrategy> cacheStrategyList) {
        var interceptor = new CacheInterceptor(cacheStrategyList);
        // pointcut
        var pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* org.jianzhao.boot.service..*.*(..))");
        // advisor
        var advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }


}
