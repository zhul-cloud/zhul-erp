package com.zhul.erp.domain.spec;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.zhul.cloud.common.exception.BizException;
import com.zhul.cloud.common.spec.ISpecification;
import com.zhul.cloud.common.spec.Notification;
import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.value.ErrorMsg;
import javax.validation.constraints.NotNull;

/**
 * Created by yanglikai on 2023/12/16.
 */
public class UserLoginSpec implements ISpecification<User> {

  private String username;

  private String password;

  public UserLoginSpec(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public boolean satisfiedBy(@NotNull User user) {

    // 1.用户名不存在
    if (user.isEmpty()) {
      throw new BizException(ErrorMsg.D0004.code(), ErrorMsg.D0004.msg());
    }

    // 2.密码效验
    String salt = user.getAccount().salt();
    String pwd = SaSecureUtil.md5BySalt(password, salt);
    if (user.getAccount().password().equals(pwd) == false) {
      throw new BizException(ErrorMsg.D0005.code(), ErrorMsg.D0005.msg());
    }

    return true;
  }

  @Override
  public boolean satisfiedBy(@NotNull User user, Notification notification) {
    return true;
  }
}
