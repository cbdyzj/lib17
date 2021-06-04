package org.jianzhao.logging.test;

import org.jianzhao.logging.EnableLogging;
import org.jianzhao.logging.Logging;
import org.jianzhao.logging.LoggingInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@EnableLogging
@SpringBootApplication(proxyBeanMethods = false)
@SpringBootTest
class LoggingStarterTest {

    @Autowired
    public DummyService dummyService;

    @MockBean
    public LoggingInterceptor loggingInterceptor;

    @Test
    public void test() throws Throwable {
        when(this.loggingInterceptor.invoke(any())).thenCallRealMethod();

        this.dummyService.call("Apple");
        var banana = this.dummyService.apply("Banana");
        assertNotNull(banana);

        verify(this.loggingInterceptor, times(2)).invoke(any());
    }

    @Service
    public static class DummyService {

        @Logging
        public String apply(String parameter) {
            return "called (" + parameter + ")";
        }

        @Logging
        public void call(String parameter) {
        }
    }
}
