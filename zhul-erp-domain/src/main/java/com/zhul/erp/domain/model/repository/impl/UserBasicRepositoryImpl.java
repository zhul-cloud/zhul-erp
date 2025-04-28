package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.UserBasic;
import com.zhul.erp.domain.model.mapper.UserBasicMapper;
import com.zhul.erp.domain.model.repository.IUserBasicRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户基本信息表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class UserBasicRepositoryImpl extends
    ServiceImpl<UserBasicMapper, UserBasic> implements
    IUserBasicRepository {

  @Override
  public UserBasic selectByUsername(String username) {
    return this.lambdaQuery().eq(UserBasic::getUsername, username).one();
  }
}
