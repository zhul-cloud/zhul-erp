package com.zhul.erp.domain.spec;

import com.zhul.cloud.common.exception.BizException;
import com.zhul.cloud.common.spec.ISpecification;
import com.zhul.cloud.common.spec.Notification;
import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.value.ErrorMsg;
import javax.validation.constraints.NotNull;

/**
 * Created by yanglikai on 2023/12/16.
 */
public class UserModifySpec implements ISpecification<User> {

  @Override
  public boolean satisfiedBy(@NotNull User user) {

    // 1.用户不存在
    if (user.isEmpty()) {
      throw new BizException(ErrorMsg.D0003.code(), ErrorMsg.D0003.msg());
    }

    return true;
  }

  @Override
  public boolean satisfiedBy(@NotNull User user, Notification notification) {
    return true;
  }
}
