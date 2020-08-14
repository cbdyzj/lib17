package org.jianzhao.java.curd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("Config update request")
public class ConfigUpdateRequest {

    @NotBlank(message = "Name cannot be empty")
    @ApiModelProperty("Name")
    private String name;

    @NotBlank(message = "Configuration value cannot be empty")
    @ApiModelProperty("Configuration value")
    private String value;

}
