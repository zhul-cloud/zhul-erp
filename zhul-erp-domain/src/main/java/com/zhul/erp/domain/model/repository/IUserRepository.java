package com.zhul.erp.domain.model.repository;


import com.zhul.erp.domain.model.aggregate.User;

/**
 * Created by yanglikai on 2023/12/19.
 */
public interface IUserRepository {

  /**
   * 保存用户
   * <p></p>
   *
   * @param user
   * @return
   */
  User save(User user);

  /**
   * 删除用户
   * <p></p>
   *
   * @param user
   * @return
   */
  User delete(User user);

  /**
   * 更新用户
   * <p></p>
   *
   * @param user
   * @return
   */
  User update(User user);
}
