package org.jianzhao.logging;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.time.Instant;
import java.util.Arrays;

public class LoggingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        println("------ " + Instant.now() + " ------");
        println("Method: " + invocation.getMethod());
        println("Arguments: " + Arrays.toString(invocation.getArguments()));
        var result = invocation.proceed();
        println("Return: " + result);
        println("------ " + Instant.now() + " ------");
        return result;
    }

    private static void println(String s) {
        System.out.println(s);
    }
}
