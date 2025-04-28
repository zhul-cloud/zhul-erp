package com.zhul.erp.client.api.passport;


import com.zhul.erp.client.dto.passport.cqrs.command.LoginCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.SignupCmd;
import com.zhul.erp.client.dto.passport.viewobject.LoginVO;

/**
 * Created by yanglikai on 2023/12/16.
 */
public interface IAuthService {

  /**
   * 注册
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean signup(SignupCmd cmd);

  /**
   * 登录
   * <p></p>
   *
   * @param cmd
   * @return
   */
  LoginVO login(LoginCmd cmd);

  /**
   * 退出
   * <p></p>
   *
   * @return
   */
  Boolean logout();
}
