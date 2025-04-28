package com.zhul.erp.domain.spec;

import com.zhul.cloud.common.exception.BizException;
import com.zhul.cloud.common.spec.ISpecification;
import com.zhul.cloud.common.spec.Notification;
import com.zhul.erp.domain.model.aggregate.RolePermission;
import com.zhul.erp.domain.model.value.ErrorMsg;
import javax.validation.constraints.NotNull;

/**
 * Created by yanglikai on 2023/12/16.
 */
public class RoleModifySpec implements ISpecification<RolePermission> {

  @Override
  public boolean satisfiedBy(@NotNull RolePermission role) {

    // 1.角色不存在
    if (role.isEmpty()) {
      throw new BizException(ErrorMsg.D0009.code(), String.format(ErrorMsg.D0009.msg()));
    }

    return true;
  }

  @Override
  public boolean satisfiedBy(@NotNull RolePermission role, Notification notification) {
    return true;
  }
}
