package draft.curd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ApiModel("Config list request")
public class ConfigListRequest {

    @Min(value = 1, message = "Illegal page index")
    @ApiModelProperty("Page index")
    private Integer pageIndex = 1;

    @Min(value = 1, message = "Illegal page size")
    @Max(value = 100, message = "Illegal page size")
    @ApiModelProperty("Page size")
    private Integer pageSize = 20;
}
