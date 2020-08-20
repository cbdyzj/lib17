package draft.curd;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Predicate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    public static final String SQL_PATH = "classpath:schema/schema.sql";

    @NonNull
    private final JdbcTemplate jdbcTemplate;

    @NonNull
    private final ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        var inputStream = this.resourceLoader.getResource(SQL_PATH).getInputStream();
        try (inputStream) {
            var sqlString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("Initialize database scheme: \n{}", sqlString);
            Arrays.stream(sqlString.split(";"))
                    .filter(Predicate.not(String::isBlank))
                    .forEach(this.jdbcTemplate::execute);
        }
    }
}
