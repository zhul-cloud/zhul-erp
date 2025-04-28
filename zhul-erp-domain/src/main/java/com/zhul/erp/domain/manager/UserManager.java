package com.zhul.erp.domain.manager;

import com.zhul.erp.domain.model.aggregate.AccountPermission;
import com.zhul.erp.domain.model.aggregate.DataPermission;
import com.zhul.erp.domain.model.aggregate.User;
import java.io.Serializable;

/**
 * Created by yanglikai on 2022/5/18.
 */
public final class UserManager implements Serializable {

  private static final ThreadLocal<User> holder = new ThreadLocal();

  private static final ThreadLocal<String> languageLocal = new ThreadLocal();

  public UserManager() {
  }

  public static void setUser(User user) {
    holder.set(user);
  }

  public static void setLanguage(String language) {
    languageLocal.set(language);
  }

  public static String getLanguages() {
    return languageLocal.get();
  }

  public static void removeLanguages() {
    languageLocal.remove();
  }


  public static User getUser() {
    return holder.get();
  }

  public static void removeUser() {
    holder.remove();
  }

  public static Integer getAccountId() {
    User user = UserManager.getUser();

    return user == null ? 0 : user.accountId();
  }

  public static Integer getUserId() {
    User user = UserManager.getUser();

    return user == null ? 0 : user.userId();
  }

  public static String getUserName() {
    User user = UserManager.getUser();

    return user == null ? "" : user.name();
  }

  public static String getWarehouseCode() {
    User user = UserManager.getUser();

    AccountPermission accountPermission = user.getAccount().getAccountPermission();

    DataPermission dataPermission = accountPermission.getDataPermission();
    if (dataPermission.hasOrgs()) {
      return dataPermission.getOrgs().get(0).getCode();
    }

    return "";
  }
}
