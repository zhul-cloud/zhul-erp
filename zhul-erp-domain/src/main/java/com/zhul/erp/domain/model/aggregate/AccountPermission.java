package com.zhul.erp.domain.model.aggregate;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 账号权限
 * <p></p>
 * Created by yanglikai on 2023/12/18.
 */
@Data
public class AccountPermission implements Serializable {

  @ApiModelProperty(value = "数据权限")
  private DataPermission dataPermission;

  public AccountPermission() {
  }

  public List<Integer> orgIds() {
    if (this.dataPermission == null) {
      return Lists.newArrayList();
    }

    return this.dataPermission.formatToInteger();
  }
}
