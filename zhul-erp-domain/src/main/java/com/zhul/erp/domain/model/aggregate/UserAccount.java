package com.zhul.erp.domain.model.aggregate;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.domain.model.entity.Account;
import com.zhul.erp.domain.model.entity.AccountAccessToken;
import com.zhul.erp.domain.model.entity.AccountLocalAuth;
import com.zhul.erp.domain.model.entity.AccountOrg;
import com.zhul.erp.domain.model.entity.AccountRole;
import com.zhul.erp.domain.model.entity.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * 用户账号域
 * <p></p>
 * Created by yanglikai on 2023/12/19.
 */
@Data
public class UserAccount implements Serializable {

  private Account account;

  private AccountLocalAuth localAuth;

  private AccountAccessToken accessToken;

  private List<AccountOrg> orgs;

  private List<AccountRole> roles;

  private List<RolePermission> rolePermissions;

  private AccountPermission accountPermission;

  public UserAccount(Account account) {
    this.account = account;
  }

  public UserAccount() {
  }

  public boolean isEmpty() {
    return this.account == null;
  }

  public boolean isNotEmpty() {
    return !this.isEmpty();
  }

  public boolean hasToken() {
    return this.accessToken != null && this.accessToken.getId() != null;
  }

  public boolean hasOrg() {
    return CollectionUtil.isNotEmpty(this.orgs);
  }

  public boolean hasRole() {
    return CollectionUtil.isNotEmpty(this.roles);
  }

  public Integer accountId() {
    return this.account.getId();
  }

  public List<String> roleCodes() {
    if (CollectionUtil.isEmpty(this.roles)) {
      return Lists.newArrayList();
    }

    return this.roles.stream().map(el -> el.getRoleCode()).collect(Collectors.toList());
  }

  public List<Integer> roleIds() {
    if (CollectionUtil.isEmpty(this.rolePermissions)) {
      return Lists.newArrayList();
    }

    List<Integer> roleIds = Lists.newArrayList();

    this.rolePermissions.forEach(permission -> roleIds.addAll(permission.formatToInteger()));

    return roleIds;
  }

  public List<Integer> orgIds() {
    if (this.accountPermission == null) {
      return Lists.newArrayList();
    }

    return this.accountPermission.orgIds();
  }

  public void linkUserId(Integer userId) {
    this.account.setUserId(userId);
  }

  public void linkAccountId(Integer accountId) {
    this.localAuth.setAccountId(accountId);

    if (this.accessToken != null) {
      this.accessToken.setAccountId(accountId);
    }

    if (CollectionUtil.isNotEmpty(this.orgs)) {
      this.orgs.forEach(el -> el.setAccountId(accountId));
    }

    if (CollectionUtil.isNotEmpty(this.roles)) {
      this.roles.forEach(el -> el.setAccountId(accountId));
    }
  }

  public String salt() {
    return this.localAuth.getSalt();
  }

  public String token() {
    return this.accessToken.getAccessToken();
  }

  public String password() {
    return this.localAuth.getPassword();
  }

  public Keyvalue defaultRole() {
    if (this.hasRole()) {
      RolePermission permission = this.rolePermissions.get(0);
      return new Keyvalue(permission.roleCode(), permission.roleName());
    }

    return new Keyvalue("", "");
  }

  public List<Resource> resources() {
    if (CollectionUtil.isEmpty(this.rolePermissions)) {
      return Lists.newArrayList();
    }

    Set<Resource> resources = Sets.newHashSet();

    this.rolePermissions
        .forEach(permission -> resources.addAll(permission.getResourcePermission().getResources()));

    return resources.stream().collect(Collectors.toList());
  }
}
