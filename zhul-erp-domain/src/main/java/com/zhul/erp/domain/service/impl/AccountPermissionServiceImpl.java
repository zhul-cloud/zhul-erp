package com.zhul.erp.domain.service.impl;

import com.zhul.erp.domain.model.aggregate.AccountPermission;
import com.zhul.erp.domain.model.aggregate.DataPermission;
import com.zhul.erp.domain.model.aggregate.UserAccount;
import com.zhul.erp.domain.model.entity.Org;
import com.zhul.erp.domain.service.IAccountPermissionService;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2024/1/10.
 */
@Service
public class AccountPermissionServiceImpl implements IAccountPermissionService {

  /**
   * 查询账号权限
   * <p></p>
   *
   * @param account
   * @return
   */
  @Override
  public AccountPermission queryAccountPermission(UserAccount account) {
    if (account.hasOrg() == false) {
      return new AccountPermission();
    }

    AccountPermission permission = new AccountPermission();

    List<String> orgCodes = account.getOrgs().stream().map(el -> el.getOrgCode())
        .collect(Collectors.toList());

//    List<Warehouse> warehouses = this.wmsGateway.loadWarehouseByCodes(orgCodes);

//    List<Org> orgs = warehouses.stream().map(warehouse -> {
//      Org org = new Org();
//      org.setId(warehouse.getId());
//      org.setCode(warehouse.getCode());
//      org.setName(warehouse.getName());
//
//      return org;
//    }).collect(Collectors.toList());

    permission.setDataPermission(new DataPermission(Lists.newArrayList()));

    return permission;
  }
}
