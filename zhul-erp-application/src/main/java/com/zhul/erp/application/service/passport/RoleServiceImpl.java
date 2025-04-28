package com.zhul.erp.application.service.passport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.exception.BizException;
import com.zhul.cloud.common.mapper.Mapper;
import com.zhul.erp.client.api.passport.IRoleService;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.RoleDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.RoleListQry;
import com.zhul.erp.client.dto.passport.viewobject.ResourceListVO;
import com.zhul.erp.client.dto.passport.viewobject.RoleDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.RoleListVO;
import com.zhul.erp.domain.mapper.MapperExpand;
import com.zhul.erp.domain.model.aggregate.RolePermission;
import com.zhul.erp.domain.model.entity.Role;
import com.zhul.erp.domain.model.repository.IRoleRepository;
import com.zhul.erp.domain.model.value.ErrorMsg;
import com.zhul.erp.domain.model.value.StatusEnum;
import com.zhul.erp.domain.service.IRoleDomainService;
import com.zhul.erp.domain.spec.RoleCreateSpec;
import com.zhul.erp.domain.spec.RoleDeleteSpec;
import com.zhul.erp.domain.spec.RoleModifySpec;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Service
public class RoleServiceImpl implements IRoleService {

  @Autowired
  private IRoleDomainService roleDomainService;

  @Autowired
  private IRoleRepository roleRepository;

  /**
   * 新增角色
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean add(RoleAddCmd cmd) {

    // 1.创建角色域
    RolePermission role = this.roleDomainService.create(cmd);

    // 2.规则效验
    new RoleCreateSpec().satisfiedBy(role);

    // 3.数据持久化
    this.roleRepository.insert(role);

    return true;
  }

  /**
   * 删除角色
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean delete(RoleDeleteCmd cmd) {

    // 1.参数解析
    int roleId = cmd.getId();

    // 2.查询角色域
    RolePermission role = this.roleDomainService.queryById(roleId);

    // 3.规则效验
    new RoleDeleteSpec().satisfiedBy(role);

    // 4.数据持久化
    this.roleRepository.remove(role);

    return true;
  }

  /**
   * 修改角色
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean modify(RoleModifyCmd cmd) {

    // 1.查询角色域
    RolePermission role = this.roleDomainService.update(cmd);

    // 2.规则效验
    new RoleModifySpec().satisfiedBy(role);

    // 3.数据持久化
    this.roleRepository.update(role);

    return true;
  }

  /**
   * 角色列表
   * <p></p>
   *
   * @param qry
   * @return
   */
  @Override
  public Page<RoleListVO> list(RoleListQry qry) {

    Page<Role> pages = this.roleRepository.page(qry.tPage(), new LambdaQueryWrapper<Role>()
        .eq(StringUtils.isNotBlank(qry.getCode()), Role::getCode, qry.getCode())
        .like(StringUtils.isNotBlank(qry.getName()), Role::getName, qry.getName())
        .eq(qry.getStatus() != null, Role::getStatus, qry.getStatus()));

    return MapperExpand.mapToPage(pages, (el -> {
      RoleListVO role = Mapper.map(el, RoleListVO.class);
      role.setStatusDesc(StatusEnum.transform(el.getStatus()).desc());
      return role;
    }));
  }

  /**
   * 角色详情
   * <p></p>
   *
   * @param qry
   * @return
   */
  @Override
  public RoleDtlVO dtl(RoleDtlQry qry) {

    // 1.查询角色域
    RolePermission domain = this.roleDomainService.queryById(qry.getId());

    // 2.规则效验
    if (domain.isEmpty()) {
      throw new BizException(ErrorMsg.D0009.code(), String.format(ErrorMsg.D0009.msg()));
    }

    // 3.数据转换
    return Stream.of(domain).map(el -> {
      Role role = el.getRole();

      RoleDtlVO vo = new RoleDtlVO();
      vo.setId(role.getId());
      vo.setCode(role.getCode());
      vo.setName(role.getName());
      vo.setStatus(role.getStatus());
      vo.setRemark(role.getRemark());
      vo.setPermissionScope(role.getPermissionScope());
      vo.setWarehouses(domain.getDataPermission().formatToKeyvalue());

      List<ResourceListVO> resources = Mapper
          .map(domain.getResourcePermission().getResources(), ResourceListVO.class);
      vo.setResources(
          resources.stream().map(resource -> resource.getId()).collect(Collectors.toList()));

      return vo;
    }).findFirst().get();
  }
}
