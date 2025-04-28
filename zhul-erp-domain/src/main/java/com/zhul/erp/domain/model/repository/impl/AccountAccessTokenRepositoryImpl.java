package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.AccountAccessToken;
import com.zhul.erp.domain.model.mapper.AccountAccessTokenMapper;
import com.zhul.erp.domain.model.repository.IAccountAccessTokenRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 账号访问Token表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class AccountAccessTokenRepositoryImpl extends
    ServiceImpl<AccountAccessTokenMapper, AccountAccessToken> implements
    IAccountAccessTokenRepository {

}
