package com.zhul.erp.client.dto.passport.cqrs.command;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class RoleModifyCmd extends RoleAddCmd {

  @NotNull(message = "主键为必填项")
  @ApiModelProperty(value = "主键", required = true)
  private Integer id;
}
