package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.AccountLocalAuth;
import com.zhul.erp.domain.model.mapper.AccountLocalAuthMapper;
import com.zhul.erp.domain.model.repository.IAccountLocalAuthRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 账号本地认证表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class AccountLocalAuthRepositoryImpl extends
    ServiceImpl<AccountLocalAuthMapper, AccountLocalAuth> implements
    IAccountLocalAuthRepository {

}
