package com.zhul.erp.client.dto.passport.cqrs.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class DictModifyCmd extends DictAddCmd {

  @ApiModelProperty(value = "主键")
  private Integer id;
}
