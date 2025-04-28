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
public class UserCreateSpec implements ISpecification<User> {

  @Override
  public boolean satisfiedBy(@NotNull User user) {

    // 1.用户名已存在
    if (user.isNotEmpty()) {
      throw new BizException(ErrorMsg.D0002.code(),
          String.format(ErrorMsg.D0002.msg(), user.username()));
    }

    return true;
  }

  @Override
  public boolean satisfiedBy(@NotNull User user, Notification notification) {
    return true;
  }
}
