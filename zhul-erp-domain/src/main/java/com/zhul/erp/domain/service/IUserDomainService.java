package com.zhul.erp.domain.service;

import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.client.dto.passport.cqrs.command.UserAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserModifyCmd;
import com.zhul.erp.domain.model.aggregate.User;
import java.util.List;

/**
 * Created by yanglikai on 2023/12/19.
 */
public interface IUserDomainService {

  /**
   * 创建用户域
   * <p></p>
   *
   * @param cmd
   * @return
   */
  User create(UserAddCmd cmd);

  /**
   * 更新用户域
   * <p></p>
   *
   * @param cmd
   * @return
   */
  User update(UserModifyCmd cmd);

  /**
   * 查询用户域
   * <p></p>
   *
   * @param userId
   * @return
   */
  User queryByUserId(Integer userId);

  /**
   * 查询用户域
   * <p></p>
   *
   * @param username
   * @return
   */
  User queryByUsername(String username);

  /**
   * 用户键值对
   * <p></p>
   *
   * @return
   */
  List<Keyvalue> keyvalues();
}
