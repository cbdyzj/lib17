package draft.curd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Config response")
public class ConfigResponse {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("配置")
    private String value;

}
