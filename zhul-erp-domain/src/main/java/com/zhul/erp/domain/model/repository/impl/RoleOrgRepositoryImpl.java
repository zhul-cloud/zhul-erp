package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.RoleOrg;
import com.zhul.erp.domain.model.mapper.RoleOrgMapper;
import com.zhul.erp.domain.model.repository.IRoleOrgRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色机构表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class RoleOrgRepositoryImpl extends
    ServiceImpl<RoleOrgMapper, RoleOrg> implements
    IRoleOrgRepository {

}
