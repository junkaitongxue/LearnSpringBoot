package com.dreamkite.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@ApiModel("一个普通的Web模型对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AModel {
    @ApiModelProperty("模型Id")
    @NonNull
    String id;

    @ApiModelProperty("模型名")
    @NotBlank
    String name;
}
