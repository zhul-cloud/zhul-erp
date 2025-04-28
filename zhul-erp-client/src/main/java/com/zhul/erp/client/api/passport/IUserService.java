package com.zhul.erp.client.api.passport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.client.dto.passport.cqrs.command.UserAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.UserDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.UserListQry;
import com.zhul.erp.client.dto.passport.viewobject.UserDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.UserListVO;
import java.util.Collection;
import java.util.List;

/**
 * Created by yanglikai on 2023/12/17.
 */
public interface IUserService {

  /**
   * 新增用户
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean add(UserAddCmd cmd);

  /**
   * 删除用户
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean delete(UserDeleteCmd cmd);

  /**
   * 修改用户
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean modify(UserModifyCmd cmd);

  /**
   * 用户列表
   * <p></p>
   *
   * @param qry
   * @return
   */
  Page<UserListVO> list(UserListQry qry);

  List<UserListVO> getByIds(Collection<Integer> ids);

  /**
   * 用户详情
   * <p></p>
   *
   * @param qry
   * @return
   */
  UserDtlVO dtl(UserDtlQry qry);

  /**
   * 用户下拉列表
   * <p></p>
   *
   * @return
   */
  List<Keyvalue> dropdownList();
}
