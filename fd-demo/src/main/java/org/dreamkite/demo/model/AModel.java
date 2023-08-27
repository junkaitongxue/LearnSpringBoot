package org.dreamkite.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("一个普通的Web模型对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AModel {
    @ApiModelProperty("模型Id")
    @NotNull
    String id;

    @ApiModelProperty("模型名")
    @NotBlank(message = "名字不能为空")
    String name;
}
