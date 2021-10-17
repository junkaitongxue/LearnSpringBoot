package com.dreamkite.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@ApiModel("一个普通的Web模型对象")
@Data
//@NoArgsConstructor
public class AModel {
    @ApiModelProperty("模型Id")
    @NonNull
    String id;

    @ApiModelProperty("模型名")
    @NonNull
    String name;
}
