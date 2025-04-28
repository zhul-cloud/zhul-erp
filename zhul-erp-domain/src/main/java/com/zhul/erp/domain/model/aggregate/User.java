package com.zhul.erp.domain.model.aggregate;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.date.DateUtil;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.domain.model.entity.AccountAccessToken;
import com.zhul.erp.domain.model.entity.Resource;
import com.zhul.erp.domain.model.entity.UserBasic;
import com.zhul.erp.domain.model.value.GlobalConstant;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * 用户聚合根
 * <p></p>
 * Created by yanglikai on 2023/12/19.
 */
@Data
public class User implements Serializable {

  private UserBasic user;

  private UserAccount account;

  public User(UserBasic user) {
    this.user = user;
  }

  public User() {
  }

  public boolean isEmpty() {
    return this.user == null || this.user.getId() == null;
  }

  public boolean isNotEmpty() {
    return !this.isEmpty();
  }

  public boolean hasRole() {
    return this.account.hasRole();
  }

  public Integer userId() {
    return this.user.getId();
  }

  public String username() {
    return this.user.getUsername();
  }

  public Integer accountId() {
    return this.account.accountId();
  }

  public String name() {
    return this.user.getName();
  }

  public String nickName() {
    return this.user.getNickname();
  }

  public Keyvalue role() {
    return this.account.defaultRole();
  }

  public List<Resource> resources() {
    return this.account.resources();
  }

  public boolean hasNoLoginRequired() {
    return this.getAccount().hasToken();
  }

  public void createToken(String token) {
    /* 创建Token */
    AccountAccessToken accessToken = new AccountAccessToken();
    accessToken.setUserId(this.userId());
    accessToken.setAccountId(this.accountId());
    accessToken.setAccessToken(token);
    accessToken.setStatus(1);
    accessToken.setExpiryTime(DateUtil.offsetDay(new Date(), 10 * 365));

    this.account.setAccessToken(accessToken);
  }

  public void resetPassword() {

    String salt = this.account.getLocalAuth().getSalt();
    String newPwd = SaSecureUtil.md5BySalt(GlobalConstant.DEFAULT_PWD, salt);

    this.account.getLocalAuth().setPassword(newPwd);
  }

  public void modifyPassword(String newPwd) {

    String salt = this.account.getLocalAuth().getSalt();
    String modifyPwd = SaSecureUtil.md5BySalt(newPwd, salt);

    this.account.getLocalAuth().setPassword(modifyPwd);
  }
}
