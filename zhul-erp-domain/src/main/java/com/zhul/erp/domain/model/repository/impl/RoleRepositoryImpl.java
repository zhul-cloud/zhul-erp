package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.aggregate.RolePermission;
import com.zhul.erp.domain.model.entity.Org;
import com.zhul.erp.domain.model.entity.Resource;
import com.zhul.erp.domain.model.entity.Role;
import com.zhul.erp.domain.model.entity.RoleOrg;
import com.zhul.erp.domain.model.entity.RoleResource;
import com.zhul.erp.domain.model.mapper.RoleMapper;
import com.zhul.erp.domain.model.repository.IRoleOrgRepository;
import com.zhul.erp.domain.model.repository.IRoleRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhul.erp.domain.model.repository.IRoleResourceRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class RoleRepositoryImpl extends
    ServiceImpl<RoleMapper, Role> implements
    IRoleRepository {

  @Autowired
  private IRoleOrgRepository roleOrgRepository;

  @Autowired
  private IRoleResourceRepository roleResourceRepository;

  @Override
  public Role selectByName(String name) {
    return this.lambdaQuery().eq(Role::getName, name).one();
  }

  @Transactional
  @Override
  public RolePermission insert(RolePermission role) {

    return this.insert(role, false);
  }

  @Override
  public RolePermission insert(RolePermission role, boolean ignoreRoleFlag) {
    // 1.新增角色信息
    if (ignoreRoleFlag == false) {
      this.save(role.getRole());
    }

    // 2.新增角色数据权限
    if (role.hasDataPermission()) {
      List<Org> orgs = role.getDataPermission().getOrgs();

      List<RoleOrg> roleOrgs = orgs.stream().map(org -> {
        RoleOrg roleOrg = new RoleOrg();
        roleOrg.setRoleCode(role.roleCode());
        roleOrg.setOrgCode(org.getCode());

        return roleOrg;
      }).collect(Collectors.toList());

      this.roleOrgRepository.saveBatch(roleOrgs);
    }

    // 3.新增角色资源权限
    if (role.hasResourcePermission()) {
      List<Resource> resources = role.getResourcePermission().getResources();

      List<RoleResource> roleResources = resources.stream().map(resource -> {
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleCode(role.roleCode());
        roleResource.setResourceId(resource.getId());
        roleResource.setResourceCode(resource.getCode());

        return roleResource;
      }).collect(Collectors.toList());

      this.roleResourceRepository.saveBatch(roleResources);
    }

    return role;
  }

  @Transactional
  @Override
  public RolePermission remove(RolePermission role) {

    return this.remove(role, false);
  }

  @Override
  public RolePermission remove(RolePermission role, boolean ignoreRoleFlag) {
    // 1.删除角色
    if (ignoreRoleFlag == false) {
      this.removeById(role.getRole());
    }

    // 2.删除角色机构
    if (role.hasDataPermission()) {
      this.roleOrgRepository.lambdaUpdate().eq(RoleOrg::getRoleCode, role.roleCode()).remove();
    }

    // 3.删除角色资源
    if (role.hasResourcePermission()) {
      this.roleResourceRepository.lambdaUpdate().eq(RoleResource::getRoleCode, role.roleCode())
          .remove();
    }

    return role;
  }

  @Transactional
  @Override
  public RolePermission update(RolePermission role) {

    // 1.更新角色
    this.updateById(role.getRole());

    // 2.删除角色关联数据
    this.remove(role, true);

    // 3.插入角色关联数据
    this.insert(role, true);

    return role;
  }
}
