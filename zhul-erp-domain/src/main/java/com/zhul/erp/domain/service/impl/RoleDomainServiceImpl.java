package com.zhul.erp.domain.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.zhul.cloud.common.mapper.Mapper;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleModifyCmd;
import com.zhul.erp.domain.manager.UserManager;
import com.zhul.erp.domain.model.aggregate.DataPermission;
import com.zhul.erp.domain.model.aggregate.ResourcePermission;
import com.zhul.erp.domain.model.aggregate.RolePermission;
import com.zhul.erp.domain.model.entity.Org;
import com.zhul.erp.domain.model.entity.Resource;
import com.zhul.erp.domain.model.entity.Role;
import com.zhul.erp.domain.model.entity.RoleOrg;
import com.zhul.erp.domain.model.entity.RoleResource;
import com.zhul.erp.domain.model.repository.IResourceRepository;
import com.zhul.erp.domain.model.repository.IRoleOrgRepository;
import com.zhul.erp.domain.model.repository.IRoleRepository;
import com.zhul.erp.domain.model.repository.IRoleResourceRepository;
import com.zhul.erp.domain.model.repository.ISerialRepository;
import com.zhul.erp.domain.service.IRoleDomainService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/18.
 */
@Service
public class RoleDomainServiceImpl implements IRoleDomainService {

  @Autowired
  private IRoleRepository roleRepository;

  @Autowired
  private IRoleOrgRepository roleOrgRepository;

  @Autowired
  private IRoleResourceRepository roleResourceRepository;

  @Autowired
  private IResourceRepository resourceRepository;

  @Autowired
  private ISerialRepository serialRepository;

  /**
   * 创建角色域
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public RolePermission create(RoleAddCmd cmd) {

    Role role = this.roleRepository.selectByName(cmd.getName());
    if (role == null) {
      // 创建角色域
      RolePermission domain = new RolePermission(Mapper.map(cmd, Role.class));

      // 创建角色编码
      if (StringUtils.isBlank(cmd.getCode())) {
        domain.getRole().setCode(this.serialRepository.roleCode());
      }

      // 创建数据权限
      if (CollectionUtil.isNotEmpty(cmd.getWarehouses())) {
        domain.setDataPermission(DataPermission.create4Orgs(cmd.getWarehouses()));
      }

      // 创建资源权限
      if (CollectionUtil.isNotEmpty(cmd.getResources())) {
        domain.setResourcePermission(ResourcePermission.create(cmd.getResources()));
      }

      return domain;
    }

    RolePermission domain = new RolePermission(role);

    return domain;
  }

  /**
   * 更新角色域
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public RolePermission update(RoleModifyCmd cmd) {

    // 1.查询角色
    Role role = this.roleRepository.getById(cmd.getId());
    if (role == null) {
      return new RolePermission();
    }

    // 2.创建角色域
    RolePermission domain = new RolePermission(role);

    // 3.更新角色数据
    domain.getRole().setName(cmd.getName());
    domain.getRole().setStatus(cmd.getStatus());
    domain.getRole().setRemark(cmd.getRemark());
    domain.getRole().setPermissionScope(cmd.getPermissionScope());
    domain.getRole().setUpdateTime(new Date());
    domain.getRole().setUpdateBy(UserManager.getUserName());

    // 4.更新数据权限
    if (CollectionUtil.isNotEmpty(cmd.getWarehouses())) {
      domain.setDataPermission(DataPermission.create4Orgs(cmd.getWarehouses()));
    }

    // 5.更新资源权限
    if (CollectionUtil.isNotEmpty(cmd.getResources())) {
      domain.setResourcePermission(ResourcePermission.create(cmd.getResources()));
    }

    // 6.返回角色域
    return domain;
  }

  /**
   * 查询角色域
   * <p></p>
   *
   * @param id
   * @return
   */
  @Override
  public RolePermission queryById(Integer id) {

    // 1.查询角色信息
    Role role = this.roleRepository.getById(id);

    // 2.查询角色域
    return this.queryByRole(role);
  }

  /**
   * 查询角色域
   * <p></p>
   *
   * @param code
   * @return
   */
  @Override
  public RolePermission queryByCode(String code) {

    // 1.查询角色信息
    Role role = this.roleRepository.lambdaQuery().eq(Role::getCode, code).one();

    // 2.查询角色域
    return this.queryByRole(role);
  }

  /**
   * 查询角色域
   * <p></p>
   *
   * @param codes
   * @return
   */
  @Override
  public List<RolePermission> queryByCodes(List<String> codes) {
    if (CollectionUtil.isEmpty(codes)) {
      return Lists.newArrayList();
    }

    List<RolePermission> permissions = Lists.newArrayListWithCapacity(codes.size());

    codes.forEach(code -> permissions.add(this.queryByCode(code)));

    return permissions;
  }

  /**
   * 查询角色域
   * <p></p>
   *
   * @param role
   * @return
   */
  @Override
  public RolePermission queryByRole(Role role) {
    // 1.查询角色信息
    if (role == null) {
      return new RolePermission();
    }

    // 2.查询角色机构
    List<RoleOrg> roleOrgs = this.roleOrgRepository.lambdaQuery()
        .eq(RoleOrg::getRoleCode, role.getCode()).list();

    List<String> orgCodes = roleOrgs.stream().map(el -> el.getOrgCode())
        .collect(Collectors.toList());

//    List<Warehouse> warehouses = this.wmsGateway.loadWarehouseByCodes(orgCodes);

//    List<Org> orgs = warehouses.stream().map(warehouse -> {
//      Org org = new Org();
//      org.setId(warehouse.getId());
//      org.setCode(warehouse.getCode());
//      org.setName(warehouse.getName());
//      return org;
//    }).collect(Collectors.toList());

    List<Org> orgs = Lists.newArrayList();

    // 3.查询角色资源
    List<RoleResource> roleResources = this.roleResourceRepository.lambdaQuery()
        .eq(RoleResource::getRoleCode, role.getCode()).list();

    List<Integer> resourceIds = roleResources.stream().map(el -> el.getResourceId()).collect(
        Collectors.toList());

    List<Resource> resources = Lists.newArrayList();
    if(CollectionUtil.isNotEmpty(resourceIds)) {
       resources = this.resourceRepository.lambdaQuery()
          .ne(Resource::getStatus, 0)
          .in(Resource::getId, resourceIds).orderByAsc(Resource::getSort).list();
    }


    // 4.创建角色域
    RolePermission domain = new RolePermission(role);
    domain.setDataPermission(new DataPermission(orgs));
    domain.setResourcePermission(new ResourcePermission(resources));


    // 5.返回角色域
    return domain;
  }
}
