package com.zhul.erp.domain.service;

import com.zhul.erp.domain.model.aggregate.UserAccount;
import com.zhul.erp.domain.model.entity.Account;
import java.util.List;

/**
 * Created by yanglikai on 2023/12/19.
 */
public interface IAccountDomainService {

  /**
   * 创建账号域
   * <p></p>
   *
   * @param username
   * @return
   */
  UserAccount create(String username);

  /**
   * 创建账号域
   * <p></p>
   *
   * @param username
   * @param password
   * @return
   */
  UserAccount create(String username, String password);

  /**
   * 创建账号域
   * <p></p>
   *
   * @param username
   * @param password
   * @param roles
   * @return
   */
  UserAccount create(String username, String password, List<String> roles);

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
  UserAccount create(String username, String password, List<String> roles, List<String> orgs);

  /**
   * 查询账号域
   * <p></p>
   *
   * @param userId
   * @return
   */
  UserAccount queryByUserId(Integer userId);

  /**
   * 查询账号域
   * <p></p>
   *
   * @param accountId
   * @return
   */
  UserAccount queryByAccountId(Integer accountId);

  /**
   * 查询账号域
   * <p></p>
   *
   * @param account
   * @return
   */
  UserAccount queryByAccountId(Account account);
}
