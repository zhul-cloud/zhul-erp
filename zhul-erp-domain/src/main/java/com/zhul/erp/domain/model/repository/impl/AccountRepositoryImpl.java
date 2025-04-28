package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.Account;
import com.zhul.erp.domain.model.mapper.AccountMapper;
import com.zhul.erp.domain.model.repository.IAccountRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 账号表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class AccountRepositoryImpl extends
    ServiceImpl<AccountMapper, Account> implements
    IAccountRepository {

}
