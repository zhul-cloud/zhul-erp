package com.zhul.erp.client.dto.passport.cqrs.query;

import com.zhul.cloud.common.model.GeneralQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class ResourceListQry extends GeneralQuery {

  @ApiModelProperty(value = "资源类型", example = "1-目录、2-菜单、3-按钮(多个使用英文逗号隔开)")
  private String type;

  @ApiModelProperty(value = "状态", example = "0-禁用、1-启用")
  private Integer status;
}
