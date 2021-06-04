# logging-spring-boot-starter

[![Release](https://jitpack.io/v/cbdyzj/logging-spring-boot-starter.svg)](https://jitpack.io/#cbdyzj/logging-spring-boot-starter)

## Usage

Prints the log when `someMethod` calls

```java
@EnableLogging
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Service
    public static class SomeService {

        @Logging
        public String someMethod(String parameter) {
            return "called (" + parameter + ")";
        }
    }
}
```

## Peer dependencies

- [org.springframework.boot:spring-boot-starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter)
- [org.aspectj:aspectjweaver](https://mvnrepository.com/artifact/org.aspectj/aspectjweaver)
