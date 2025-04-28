package com.zhul.erp.client.dto.passport.cqrs.query;

import com.zhul.cloud.common.model.GeneralQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class RoleListQry extends GeneralQuery {

  @ApiModelProperty(value = "角色编码", example = "支持模糊查询")
  private String code;

  @ApiModelProperty(value = "角色名称", example = "支持模糊查询")
  private String name;

  @ApiModelProperty(value = "角色状态", example = "0-禁用、1-启用")
  private Integer status;
}
