package com.zhul.erp.client.api.passport;


import com.zhul.erp.client.dto.passport.cqrs.command.PasswordModifyCmd;

/**
 * Created by yanglikai on 2023/12/16.
 */
public interface IPasswordService {

  /**
   * 重置密码
   * <p></p>
   *
   * @param userId
   * @return
   */
  Boolean reset(Integer userId);

  /**
   * 修改密码
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean modify(PasswordModifyCmd cmd);
}
