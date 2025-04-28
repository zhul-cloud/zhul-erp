package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.aggregate.UserAccount;
import com.zhul.erp.domain.model.entity.AccountOrg;
import com.zhul.erp.domain.model.entity.AccountRole;
import com.zhul.erp.domain.model.repository.IAccountAccessTokenRepository;
import com.zhul.erp.domain.model.repository.IAccountLocalAuthRepository;
import com.zhul.erp.domain.model.repository.IAccountOrgRepository;
import com.zhul.erp.domain.model.repository.IAccountRepository;
import com.zhul.erp.domain.model.repository.IAccountRoleRepository;
import com.zhul.erp.domain.model.repository.IUserBasicRepository;
import com.zhul.erp.domain.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yanglikai on 2023/12/19.
 */
@Repository
public class UserRepositoryImpl implements IUserRepository {

  @Autowired
  private IUserBasicRepository userBasicRepository;

  @Autowired
  private IAccountRepository accountRepository;

  @Autowired
  private IAccountLocalAuthRepository localAuthRepository;

  @Autowired
  private IAccountAccessTokenRepository accountAccessTokenRepository;

  @Autowired
  private IAccountOrgRepository accountOrgRepository;

  @Autowired
  private IAccountRoleRepository accountRoleRepository;

  /**
   * 保存用户
   * <p></p>
   *
   * @param user
   * @return
   */
  @Transactional
  @Override
  public User save(User user) {

    // 1.持久化用户
    this.userBasicRepository.save(user.getUser());

    // 2.账号关联用户
    UserAccount account = user.getAccount();
    account.linkUserId(user.userId());

    // 3.持久化账号
    this.accountRepository.save(account.getAccount());

    // 4.账号关联账号认证等
    account.linkAccountId(account.accountId());

    // 4.持久化账号认证
    this.localAuthRepository.save(account.getLocalAuth());

    // 5.持久化账号机构
    if (account.hasOrg()) {
      this.accountOrgRepository.saveBatch(account.getOrgs());
    }

    // 6.持久化账号角色
    if (account.hasRole()) {
      this.accountRoleRepository.saveBatch(account.getRoles());
    }

    return user;
  }

  /**
   * 删除用户
   * <p></p>
   *
   * @param user
   * @return
   */
  @Transactional
  @Override
  public User delete(User user) {

    // 1.删除用户信息
    this.userBasicRepository.removeById(user.getUser());

    // 2.删除账号信息
    UserAccount account = user.getAccount();
    this.accountRepository.removeById(account.getAccount());

    // 3.删除账号认证信息
    this.localAuthRepository.removeById(account.getLocalAuth());

    // 4.删除账号Token信息
    if (account.hasToken()) {
      this.accountAccessTokenRepository.removeById(account.getAccessToken());
    }

    // 5.删除账号机构信息
    if (account.hasOrg()) {
      this.accountOrgRepository.removeBatchByIds(account.getOrgs());
    }

    // 6.删除账号角色信息
    if (account.hasRole()) {
      this.accountRoleRepository.removeBatchByIds(account.getRoles());
    }

    return user;
  }

  /**
   * 更新用户
   * <p></p>
   *
   * @param user
   * @return
   */
  @Transactional
  @Override
  public User update(User user) {

    // 1.更新用户信息
    this.userBasicRepository.updateById(user.getUser());

    // 2.更新账号机构信息
    UserAccount account = user.getAccount();
    if (account.hasOrg()) {
      this.accountOrgRepository.lambdaUpdate().eq(AccountOrg::getAccountId, user.accountId())
          .remove();

      this.accountOrgRepository.saveBatch(account.getOrgs());
    }

    // 3.更新账号角色信息
    if (account.hasRole()) {
      this.accountRoleRepository.lambdaUpdate().eq(AccountRole::getAccountId, user.accountId())
          .remove();

      this.accountRoleRepository.saveBatch(account.getRoles());
    }

    return user;
  }
}
