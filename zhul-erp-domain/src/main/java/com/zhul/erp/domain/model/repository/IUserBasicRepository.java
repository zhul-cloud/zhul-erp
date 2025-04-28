package com.zhul.erp.domain.model.repository;

import com.zhul.erp.domain.model.entity.UserBasic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户基本信息表 Repository 接口
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
public interface IUserBasicRepository extends IService<UserBasic> {

  UserBasic selectByUsername(String username);
}
