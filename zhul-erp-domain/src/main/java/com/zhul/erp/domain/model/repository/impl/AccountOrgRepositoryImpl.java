package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.AccountOrg;
import com.zhul.erp.domain.model.mapper.AccountOrgMapper;
import com.zhul.erp.domain.model.repository.IAccountOrgRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 账号机构表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class AccountOrgRepositoryImpl extends
    ServiceImpl<AccountOrgMapper, AccountOrg> implements
    IAccountOrgRepository {

}
