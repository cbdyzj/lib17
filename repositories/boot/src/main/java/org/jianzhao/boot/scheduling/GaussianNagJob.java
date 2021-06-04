package org.jianzhao.boot.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class GaussianNagJob {

    @Scheduled(fixedDelay = 5000)
    public void nag() {
        var random = ThreadLocalRandom.current();
        log.info("{}", random.nextGaussian());
    }

}
