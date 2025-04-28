package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.Resource;
import com.zhul.erp.domain.model.mapper.ResourceMapper;
import com.zhul.erp.domain.model.repository.IResourceRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 资源表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class ResourceRepositoryImpl extends
    ServiceImpl<ResourceMapper, Resource> implements
    IResourceRepository {

}
