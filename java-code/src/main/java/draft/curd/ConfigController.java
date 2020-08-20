package draft.curd;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "配置管理")
@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {

    @NonNull
    private final ConfigService configService;

    @ApiOperation("新增配置")
    @PostMapping("/config")
    public ResponseEntity<?> addConfig(@Validated @RequestBody ConfigUpdateRequest request) {
        var config = new Config();
        BeanUtils.copyProperties(request, config);
        Config config2 = this.configService.addConfig(config);
        return ResponseEntity.ok(config2);
    }

    @ApiOperation("查询配置")
    @GetMapping("/config")
    public ResponseEntity<?> queryConfig(@RequestParam("name") String name) {
        Config config = this.configService.findConfigByName(name);
        var response = new ConfigResponse();
        BeanUtils.copyProperties(config, response);
        return ResponseEntity.ok(response);
    }

    @ApiOperation("查询配置列表")
    @PostMapping("/list")
    public ResponseEntity<?> list(@Validated @RequestBody ConfigListRequest request) {
        var page = request.getPageIndex();
        var size = request.getPageSize();
        List<Config> configs = this.configService.listConfig(page, size);
        List<ConfigResponse> responseList = new ArrayList<>();
        for (var config : configs) {
            var response = new ConfigResponse();
            BeanUtils.copyProperties(config, response);
            responseList.add(response);
        }
        return ResponseEntity.ok(responseList);
    }
}
