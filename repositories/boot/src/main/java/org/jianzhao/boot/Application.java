package org.jianzhao.boot;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.Objects;

@SpringBootApplication(excludeName = Application.EXCLUDE_CONFIGURATION)
public class Application implements ApplicationContextAware {

    public static final String EXCLUDE_CONFIGURATION
            = "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration";

    private static ApplicationContext context;

    public static ApplicationContext context() {
        return Objects.requireNonNull(context);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
        Application.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
