package draft.curd;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import draft.curd.Config;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ConfigRepository {

    @NonNull
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Config findById(Integer id) {
        var sql = "SELECT id, name, value FROM config WHERE id = :id";
        var rowMapper = new BeanPropertyRowMapper<>(Config.class);
        List<Config> configs = this.namedParameterJdbcTemplate.query(
                sql, Map.of("id", id), rowMapper);
        return CollectionUtils.isEmpty(configs) ? null : configs.get(0);
    }

    public Config findByName(String name) {
        var sql = "SELECT id, name, value FROM config WHERE name = :name LIMIT 1";
        var rowMapper = new BeanPropertyRowMapper<>(Config.class);
        List<Config> configs = this.namedParameterJdbcTemplate.query(
                sql, Map.of("name", name), rowMapper);
        return CollectionUtils.isEmpty(configs) ? null : configs.get(0);
    }

    public Config create(Config config) {
        var sql = "INSERT INTO config (name, value) VALUES (:name, :value)";
        var paramSource = new BeanPropertySqlParameterSource(config);
        var keyHolder = new GeneratedKeyHolder();
        this.namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
        var config2 = new Config();
        BeanUtils.copyProperties(config, config2);
        var id = keyHolder.getKey();
        if (id != null) {
            config2.setId(id.intValue());
        }
        return config2;
    }

    public void deleteById(Integer id) {
        var sql = "DELETE FROM config WHERE id = :id";
        this.namedParameterJdbcTemplate.update(sql, Map.of("id", id));
    }

    public List<Config> findAll(Integer limit, Integer offset) {
        var sql = "SELECT id, name, value FROM config LIMIT :limit OFFSET :offset";
        var rowMapper = new BeanPropertyRowMapper<>(Config.class);
        var paramMap = Map.of("limit", limit, "offset", offset);
        return this.namedParameterJdbcTemplate.query(sql, paramMap, rowMapper);
    }
}
