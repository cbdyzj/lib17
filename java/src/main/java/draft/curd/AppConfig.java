package draft.curd;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppConfig {

    @NonNull
    private final ConfigService configService;

    public String get(String name) {
        var config = this.configService.findConfigByName(name);
        if (config == null) {
            throw new IllegalStateException("App config not found: " + name);
        }
        return config.getValue();
    }
}
