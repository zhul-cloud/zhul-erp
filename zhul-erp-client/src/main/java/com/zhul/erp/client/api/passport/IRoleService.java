package com.zhul.erp.client.api.passport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.RoleDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.RoleListQry;
import com.zhul.erp.client.dto.passport.viewobject.RoleDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.RoleListVO;

/**
 * Created by yanglikai on 2023/12/17.
 */
public interface IRoleService {

  /**
   * 新增角色
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean add(RoleAddCmd cmd);

  /**
   * 删除角色
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean delete(RoleDeleteCmd cmd);

  /**
   * 修改角色
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean modify(RoleModifyCmd cmd);

  /**
   * 角色列表
   * <p></p>
   *
   * @param qry
   * @return
   */
  Page<RoleListVO> list(RoleListQry qry);

  /**
   * 角色详情
   * <p></p>
   *
   * @param qry
   * @return
   */
  RoleDtlVO dtl(RoleDtlQry qry);
}
