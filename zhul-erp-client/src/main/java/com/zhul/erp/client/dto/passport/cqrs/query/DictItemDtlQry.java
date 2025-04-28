package com.zhul.erp.client.dto.passport.cqrs.query;

import com.zhul.cloud.common.model.GeneralQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class DictItemDtlQry extends GeneralQuery {

  @ApiModelProperty(value = "主键")
  private Integer id;
}
