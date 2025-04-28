package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.AccountRole;
import com.zhul.erp.domain.model.mapper.AccountRoleMapper;
import com.zhul.erp.domain.model.repository.IAccountRoleRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 账号角色表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class AccountRoleRepositoryImpl extends
    ServiceImpl<AccountRoleMapper, AccountRole> implements
    IAccountRoleRepository {

}
