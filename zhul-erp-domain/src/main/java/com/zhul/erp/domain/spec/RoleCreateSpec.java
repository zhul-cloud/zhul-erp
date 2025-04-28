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
public class RoleCreateSpec implements ISpecification<RolePermission> {

  @Override
  public boolean satisfiedBy(@NotNull RolePermission domain) {
    // 1.角色名称重复
    if (domain.isNotEmpty()) {
      throw new BizException(ErrorMsg.D0001.code(),
          String.format(ErrorMsg.D0001.msg(), domain.getRole().getName()));
    }

    return true;
  }

  @Override
  public boolean satisfiedBy(@NotNull RolePermission domain, Notification notification) {
    return true;
  }
}
