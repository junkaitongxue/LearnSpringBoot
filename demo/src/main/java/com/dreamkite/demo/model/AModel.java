package com.dreamkite.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("一个普通的Web组件")
@Data
public class AModel {
    @ApiModelProperty("模型Id")
    String id;
}
