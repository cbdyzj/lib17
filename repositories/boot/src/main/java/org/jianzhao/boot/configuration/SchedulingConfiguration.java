package org.jianzhao.boot.configuration;

import lombok.extern.slf4j.Slf4j;
import org.jianzhao.boot.scheduling.GaussianNagJob;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(prefix = "spring.application", name = "scheduling")
@Slf4j
public class SchedulingConfiguration {

    @Bean
    public GaussianNagJob gaussianNagJob() {
        return new GaussianNagJob();
    }
}
