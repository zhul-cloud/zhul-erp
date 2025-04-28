package com.zhul.erp.domain.model.repository;

import com.zhul.erp.domain.model.aggregate.RolePermission;
import com.zhul.erp.domain.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 Repository 接口
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
public interface IRoleRepository extends IService<Role> {

  Role selectByName(String name);

  RolePermission insert(RolePermission role);

  RolePermission insert(RolePermission role, boolean ignoreRoleFlag);

  RolePermission remove(RolePermission role);

  RolePermission remove(RolePermission role, boolean ignoreRoleFlag);

  RolePermission update(RolePermission role);
}
