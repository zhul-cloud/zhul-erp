package com.zhul.erp.application.service.passport;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhul.erp.application.cqrs.passport.model.LoginMapper;
import com.zhul.erp.client.api.passport.IAuthService;
import com.zhul.erp.client.api.passport.IUserService;
import com.zhul.erp.client.dto.passport.cqrs.command.LoginCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.SignupCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserAddCmd;
import com.zhul.erp.client.dto.passport.viewobject.LoginVO;
import com.zhul.erp.domain.manager.UserManager;
import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.entity.AccountAccessToken;
import com.zhul.erp.domain.model.repository.IAccountAccessTokenRepository;
import com.zhul.erp.domain.model.value.UserTypeEnum;
import com.zhul.erp.domain.service.ILoginDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/16.
 */
@Service
public class AuthServiceImpl implements IAuthService {

  @Autowired
  private ILoginDomainService loginDomainService;

  @Autowired
  private IUserService userService;

  @Autowired
  private IAccountAccessTokenRepository accountAccessTokenRepository;

  /**
   * 注册
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean signup(SignupCmd cmd) {

    UserAddCmd userAddCmd = new UserAddCmd();
    userAddCmd.setType(UserTypeEnum.TYPE_1.code());
    userAddCmd.setName(cmd.getPhone());
    userAddCmd.setUsername(cmd.getUsername());
    userAddCmd.setPassword(cmd.getPassword());
    userAddCmd.setPhone(cmd.getPhone());
    userAddCmd.setStatus(1);

    this.userService.add(userAddCmd);

    return true;
  }

  /**
   * 登录
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public LoginVO login(LoginCmd cmd) {

    // 1.参数解析
    String username = cmd.getUsername();
    String password = cmd.getPassword();

    // 2.认证
    User user = this.loginDomainService.auth(username, password);

    // 3.是否免登陆
    if (user.hasNoLoginRequired()) {
      return new LoginMapper(user).mapToVO();
    }

    // 4.登录
    this.loginDomainService.login(user);

    // 5.数据持久化
    this.accountAccessTokenRepository.save(user.getAccount().getAccessToken());

    // 6.数据转换
    return new LoginMapper(user).mapToVO();
  }

  /**
   * 退出
   * <p></p>
   *
   * @return
   */
  @Override
  public Boolean logout() {

    int accountId = UserManager.getAccountId();

    this.accountAccessTokenRepository.remove(
        new UpdateWrapper<AccountAccessToken>().eq(AccountAccessToken.ACCOUNT_ID, accountId));

    return true;
  }
}
