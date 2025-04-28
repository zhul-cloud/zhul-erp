package com.zhul.erp.client.dto.passport.cqrs.query;

import com.zhul.cloud.common.model.GeneralQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class DictListQry extends GeneralQuery {

  @ApiModelProperty(value = "搜索键", example = "支持编码/名称搜索")
  private String searchKey;
}
