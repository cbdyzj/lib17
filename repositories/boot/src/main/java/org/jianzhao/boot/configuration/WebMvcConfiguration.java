package org.jianzhao.boot.configuration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jianzhao.boot.support.MustacheCompiler;
import org.jianzhao.boot.support.cache.ContextCacheStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    @NonNull
    public ContextCacheStrategy contextCacheStrategy;

    @Bean
    public MustacheCompiler mustacheCompiler() {
        return new MustacheCompiler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        var interceptor = this.contextCacheStrategy.getContextCacheCleanInterceptor();
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
