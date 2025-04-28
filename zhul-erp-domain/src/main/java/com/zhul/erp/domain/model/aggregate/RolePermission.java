package com.zhul.erp.domain.model.aggregate;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.domain.model.entity.Resource;
import com.zhul.erp.domain.model.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 角色域
 * <p></p>
 * Created by yanglikai on 2023/12/18.
 */
@Data
public class RolePermission implements Serializable {

  @ApiModelProperty(value = "角色")
  private Role role;

  @ApiModelProperty(value = "数据权限")
  private DataPermission dataPermission;

  @ApiModelProperty(value = "资源权限")
  private ResourcePermission resourcePermission;

  public RolePermission(Role role) {
    this.role = role;
  }

  public RolePermission() {
  }

  public boolean isEmpty() {
    return this.role == null || this.role.isEmpty();
  }

  public boolean isNotEmpty() {
    return !this.isEmpty();
  }

  public boolean hasDataPermission() {
    return this.dataPermission != null && CollectionUtil.isNotEmpty(this.dataPermission.getOrgs());
  }

  public boolean hasResourcePermission() {
    return this.resourcePermission != null && CollectionUtil
        .isNotEmpty(this.resourcePermission.getResources());
  }

  public Integer roleId() {
    if (this.role == null) {
      return 0;
    }

    return this.role.getId();
  }

  public String roleCode() {
    if (this.role == null) {
      return "";
    }

    return this.role.getCode();
  }

  public String roleName() {
    if (this.role == null) {
      return "";
    }

    return this.role.getName();
  }

  public List<Resource> resources() {
    if (this.resourcePermission == null) {
      return Lists.newArrayList();
    }

    return this.resourcePermission.getResources();
  }

  public List<Keyvalue> formatToKeyvalue() {

    return Lists.newArrayList(new Keyvalue(this.roleCode(), this.roleName()));
  }

  public List<Integer> formatToInteger() {
    return Lists.newArrayList(this.roleId());
  }

  public List<String> formatToString() {
    return Lists.newArrayList(this.roleCode());
  }
}
