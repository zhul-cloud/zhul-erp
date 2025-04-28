package com.zhul.erp.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.aggregate.UserGrant;
import com.zhul.erp.domain.service.ILoginDomainService;
import com.zhul.erp.domain.service.IUserDomainService;
import com.zhul.erp.domain.spec.UserLoginSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/25.
 */
@Service
public class LoginDomainServiceImpl implements ILoginDomainService {

  @Autowired
  private IUserDomainService userDomainService;

  /**
   * 认证
   * <p></p>
   *
   * @param username
   * @param password
   * @return
   */
  @Override
  public User auth(String username, String password) {

    // 1.查询用户
    User user = this.userDomainService.queryByUsername(username);

    // 2.规则效验
    new UserLoginSpec(username, password).satisfiedBy(user);

    // 3.返回结果
    return user;
  }

  /**
   * 登录
   * <p></p>
   *
   * @param user
   * @return
   */
  @Override
  public User login(User user) {
    // 1.用户登录
    StpUtil.login(user.userId());

    // 2.创建Token
    String token = StpUtil.getTokenValue();
    user.createToken(token);

    // 3.返回结果
    return user;
  }

  /**
   * 授权
   * <p></p>
   *
   * @param user
   * @return
   */
  @Override
  public UserGrant grant(User user) {

    return new UserGrant(user);
  }
}
