package com.zhul.erp.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.zhul.erp.domain.manager.UserManager;
import com.zhul.erp.domain.model.aggregate.UserAccount;
import com.zhul.erp.domain.model.entity.Account;
import com.zhul.erp.domain.model.entity.AccountAccessToken;
import com.zhul.erp.domain.model.entity.AccountLocalAuth;
import com.zhul.erp.domain.model.entity.AccountOrg;
import com.zhul.erp.domain.model.entity.AccountRole;
import com.zhul.erp.domain.model.repository.IAccountAccessTokenRepository;
import com.zhul.erp.domain.model.repository.IAccountLocalAuthRepository;
import com.zhul.erp.domain.model.repository.IAccountOrgRepository;
import com.zhul.erp.domain.model.repository.IAccountRepository;
import com.zhul.erp.domain.model.repository.IAccountRoleRepository;
import com.zhul.erp.domain.model.value.GlobalConstant;
import com.zhul.erp.domain.service.IAccountDomainService;
import com.zhul.erp.domain.service.IAccountPermissionService;
import com.zhul.erp.domain.service.IRoleDomainService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/19.
 */
@Service
public class AccountDomainServiceImpl implements IAccountDomainService {

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

  @Autowired
  private IRoleDomainService roleDomainService;

  @Autowired
  private IAccountPermissionService accountPermissionService;

  /**
   * 创建账号
   * <p></p>
   *
   * @param username
   * @return
   */
  @Override
  public UserAccount create(String username) {

    return this.create(username, GlobalConstant.DEFAULT_PWD);
  }

  /**
   * 创建账号
   * <p></p>
   *
   * @param username
   * @param password
   * @return
   */
  @Override
  public UserAccount create(String username, String password) {

    // 1.创建账号
    Account account = new Account();
    account.setUsername(username);

    // 2.创建账号认证
    String salt = SaFoxUtil.getRandomString(64);
    AccountLocalAuth localAuth = new AccountLocalAuth();
    localAuth.setAccountId(account.getId());
    localAuth.setUsername(username);
    localAuth.setSalt(salt);
    localAuth.setPassword(SaSecureUtil.md5BySalt(password, salt));
    localAuth.setCreateBy(UserManager.getUserName());
    localAuth.setUpdateBy(UserManager.getUserName());

    // 3.创建账号域
    UserAccount userAccount = new UserAccount();
    userAccount.setAccount(account);
    userAccount.setLocalAuth(localAuth);

    return userAccount;
  }

  /**
   * 创建账号域
   * <p></p>
   *
   * @param username
   * @param password
   * @param roles
   * @return
   */
  @Override
  public UserAccount create(String username, String password, List<String> roles) {
    // 1.创建账号域
    UserAccount account = this.create(username, password);
    if (CollectionUtil.isEmpty(roles)) {
      return account;
    }

    // 2.创建账号角色
    account.setRoles(roles.stream().map(roleCode -> {
      AccountRole accountRole = new AccountRole();
      accountRole.setRoleCode(roleCode);
      return accountRole;
    }).collect(Collectors.toList()));

    // 3.创建账号角色权限
    account.setRolePermissions(this.roleDomainService.queryByCodes(roles));

    return account;
  }

  /**
   * 创建账号域
   * <p></p>
   *
   * @param username
   * @param password
   * @param roles
   * @param orgs
   * @return
   */
  @Override
  public UserAccount create(String username, String password, List<String> roles,
      List<String> orgs) {
    // 1.创建账号域
    UserAccount account = this.create(username, password, roles);
    if (CollectionUtil.isEmpty(orgs)) {
      return account;
    }

    // 2.创建账号机构
    account.setOrgs(orgs.stream().map(orgCode -> {
      AccountOrg accountOrg = new AccountOrg();
      accountOrg.setOrgCode(orgCode);
      return accountOrg;
    }).collect(Collectors.toList()));

    return account;
  }

  /**
   * 查询账号域
   * <p></p>
   *
   * @param userId
   * @return
   */
  @Override
  public UserAccount queryByUserId(Integer userId) {

    Account account = this.accountRepository.lambdaQuery().eq(Account::getUserId, userId).one();

    return this.queryByAccountId(account);
  }

  /**
   * 查询账号域
   * <p></p>
   *
   * @param accountId
   * @return
   */
  @Override
  public UserAccount queryByAccountId(Integer accountId) {

    Account account = this.accountRepository.getById(accountId);

    return this.queryByAccountId(account);
  }

  /**
   * 查询账号域
   * <p></p>
   *
   * @param account
   * @return
   */
  @Override
  public UserAccount queryByAccountId(Account account) {
    // 1.查询账号信息
    if (account == null) {
      return new UserAccount();
    }

    // 2.查询账号相关信息
    UserAccount userAccount = new UserAccount(account);

    // 2.1.查询账号认证信息
    userAccount.setLocalAuth(
        this.localAuthRepository.lambdaQuery().eq(AccountLocalAuth::getAccountId, account.getId())
            .one());

    // 2.2.查询账号Token信息
    userAccount.setAccessToken(this.accountAccessTokenRepository.lambdaQuery()
        .eq(AccountAccessToken::getAccountId, account.getId()).one());

    // 2.3.查询账号机构信息
    userAccount.setOrgs(
        this.accountOrgRepository.lambdaQuery().eq(AccountOrg::getAccountId, account.getId())
            .list());

    // 2.4.查询账号角色信息
    userAccount.setRoles(
        this.accountRoleRepository.lambdaQuery().eq(AccountRole::getAccountId, account.getId())
            .list());

    // 2.5.查询账号角色权限信息
    userAccount.setRolePermissions(this.roleDomainService.queryByCodes(userAccount.roleCodes()));

    // 2.6.查询账号权限信息
    userAccount
        .setAccountPermission(this.accountPermissionService.queryAccountPermission(userAccount));

    return userAccount;
  }
}
