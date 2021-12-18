package draft.curd;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigService {

    @NonNull
    private final ConfigRepository configRepository;

    @Transactional
    public Config addConfig(Config config) {
        var exist = this.configRepository.findByName(config.getName());
        Assert.isNull(exist, "配置名已存在，请更换其他名字！");
        return this.configRepository.create(config);
    }

    public Config findConfigById(Integer configId) {
        var config = this.configRepository.findById(configId);
        Assert.notNull(config, "配置不存在！");
        return config;
    }

    public Config findConfigByName(String name) {
        var config = this.configRepository.findByName(name);
        Assert.notNull(config, "配置不存在！");
        return config;
    }

    public void removeConfig(Integer id) {
        var exist = this.configRepository.findById(id);
        if (exist == null) {
            return;
        }
        this.configRepository.deleteById(id);
    }

    public List<Config> listConfig(Integer pageIndex, Integer pageSize) {
        return this.configRepository.findAll(pageSize, (pageIndex - 1) * pageSize);
    }
}
