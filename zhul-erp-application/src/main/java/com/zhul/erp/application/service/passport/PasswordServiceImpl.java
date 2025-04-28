package com.zhul.erp.application.service.passport;

import com.zhul.erp.client.api.passport.IPasswordService;
import com.zhul.erp.client.dto.passport.cqrs.command.PasswordModifyCmd;
import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.entity.AccountLocalAuth;
import com.zhul.erp.domain.model.repository.IAccountLocalAuthRepository;
import com.zhul.erp.domain.service.IUserDomainService;
import com.zhul.erp.domain.spec.UserPasswordModifySpec;
import com.zhul.erp.domain.spec.UserPasswordResetSpec;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/16.
 */
@Service
public class PasswordServiceImpl implements IPasswordService {

  @Autowired
  private IUserDomainService userDomainService;

  @Autowired
  private IAccountLocalAuthRepository localAuthRepository;

  /**
   * 重置密码
   * <p></p>
   *
   * @param userId
   * @return
   */
  @Override
  public Boolean reset(Integer userId) {

    // 1.用户查询
    User user = this.userDomainService.queryByUserId(userId);

    // 2.规则效验
    new UserPasswordResetSpec().satisfiedBy(user);

    // 3.重置密码
    user.resetPassword();

    // 4.数据持久化
    this.localAuthRepository.lambdaUpdate()
        .set(AccountLocalAuth::getPassword, user.getAccount().password())
        .set(AccountLocalAuth::getUpdateTime, new Date())
        .eq(AccountLocalAuth::getAccountId, user.accountId()).update();

    return true;
  }

  /**
   * 修改密码
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean modify(PasswordModifyCmd cmd) {
    // 1.参数解析
    int userId = cmd.getUserId();
    String oldPassword = cmd.getOldPassword();
    String newPassword = cmd.getNewPassword();

    // 2.用户查询
    User user = this.userDomainService.queryByUserId(userId);

    // 3.规则效验
    new UserPasswordModifySpec(oldPassword).satisfiedBy(user);

    // 4.修改密码
    user.modifyPassword(newPassword);

    // 5.数据持久化
    this.localAuthRepository.lambdaUpdate()
        .set(AccountLocalAuth::getPassword, user.getAccount().password())
        .set(AccountLocalAuth::getUpdateTime, new Date())
        .eq(AccountLocalAuth::getAccountId, user.accountId()).update();

    return true;
  }
}
