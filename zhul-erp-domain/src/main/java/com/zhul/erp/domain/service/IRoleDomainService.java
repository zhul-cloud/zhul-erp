package com.zhul.erp.domain.service;

import com.zhul.erp.client.dto.passport.cqrs.command.RoleAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleModifyCmd;
import com.zhul.erp.domain.model.aggregate.RolePermission;
import com.zhul.erp.domain.model.entity.Role;
import java.util.List;

/**
 * Created by yanglikai on 2023/12/18.
 */
public interface IRoleDomainService {

  /**
   * 创建角色域
   * <p></p>
   *
   * @param cmd
   * @return
   */
  RolePermission create(RoleAddCmd cmd);

  /**
   * 更新角色域
   * <p></p>
   *
   * @param cmd
   * @return
   */
  RolePermission update(RoleModifyCmd cmd);

  /**
   * 查询角色域
   * <p></p>
   *
   * @param id
   * @return
   */
  RolePermission queryById(Integer id);

  /**
   * 查询角色域
   * <p></p>
   *
   * @param code
   * @return
   */
  RolePermission queryByCode(String code);

  /**
   * 查询角色域
   * <p></p>
   *
   * @param codes
   * @return
   */
  List<RolePermission> queryByCodes(List<String> codes);

  /**
   * 查询角色域
   * <p></p>
   *
   * @param role
   * @return
   */
  RolePermission queryByRole(Role role);
}
