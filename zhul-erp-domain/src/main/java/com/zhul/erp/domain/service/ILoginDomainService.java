package com.zhul.erp.domain.service;


import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.aggregate.UserGrant;

/**
 * Created by yanglikai on 2023/12/25.
 */
public interface ILoginDomainService {

  /**
   * 认证
   * <p></p>
   *
   * @param username
   * @param password
   * @return
   */
  User auth(String username, String password);

  /**
   * 登录
   * <p></p>
   *
   * @param user
   * @return
   */
  User login(User user);

  /**
   * 授权
   * <p></p>
   *
   * @param user
   * @return
   */
  UserGrant grant(User user);
}
