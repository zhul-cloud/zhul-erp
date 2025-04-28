package com.zhul.erp.domain.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.zhul.cloud.common.model.Keyvalue;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yanglikai on 2023/12/19.
 */
public final class MdmCacheManager implements Serializable {

  private static final Cache<String, Object> cache;

  static {
    cache = CacheBuilder.newBuilder().maximumSize(50000).build();
  }

  public MdmCacheManager() {
  }

  public static Object getFromCache(String key) {
    return cache.getIfPresent(key);
  }

  public static void putInCache(String key, Object value) {
    cache.put(key, value);
  }

  public final class CacheKey implements Serializable {

    public static final String MDM_DEPT_KEY = "mdm:dept:%s";

    public static final String MDM_ROLE_KEY = "mdm:role:%s";

    public static final String MDM_USER_KEY = "mdm:user:%s";
  }

  public static Keyvalue getDept(String deptId) {
    String key = String.format(CacheKey.MDM_DEPT_KEY, deptId);

    Keyvalue dept = (Keyvalue) MdmCacheManager.getFromCache(key);

    return dept == null ? new Keyvalue("", "") : dept;
  }

  public static Keyvalue getRole(String roleCode) {
    String key = String.format(CacheKey.MDM_ROLE_KEY, roleCode);

    Keyvalue role = (Keyvalue) MdmCacheManager.getFromCache(key);

    return role == null ? new Keyvalue("", "") : role;
  }

  public static List<Keyvalue> getRoles(List<String> roleCodes) {

    List<Keyvalue> roles = Lists.newArrayListWithCapacity(roleCodes.size());

    roleCodes.forEach(roleCode -> {
      String key = String.format(CacheKey.MDM_ROLE_KEY, roleCode);

      Keyvalue role = (Keyvalue) MdmCacheManager.getFromCache(key);

      roles.add(role);
    });

    return roles;
  }

  public static Keyvalue getUser(Integer userId) {
    String key = String.format(CacheKey.MDM_USER_KEY, userId);

    Keyvalue user = (Keyvalue) MdmCacheManager.getFromCache(key);

    return user == null ? new Keyvalue("", "") : user;
  }
}
