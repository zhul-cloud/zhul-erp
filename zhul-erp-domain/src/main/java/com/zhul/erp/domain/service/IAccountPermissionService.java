package com.zhul.erp.domain.service;


import com.zhul.erp.domain.model.aggregate.AccountPermission;
import com.zhul.erp.domain.model.aggregate.UserAccount;

/**
 * Created by yanglikai on 2024/1/10.
 */
public interface IAccountPermissionService {

  /**
   * 查询账号权限
   * <p></p>
   *
   * @param account
   * @return
   */
  AccountPermission queryAccountPermission(UserAccount account);
}
