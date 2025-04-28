package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.RoleResource;
import com.zhul.erp.domain.model.mapper.RoleResourceMapper;
import com.zhul.erp.domain.model.repository.IRoleResourceRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色资源表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class RoleResourceRepositoryImpl extends
    ServiceImpl<RoleResourceMapper, RoleResource> implements
    IRoleResourceRepository {

}
