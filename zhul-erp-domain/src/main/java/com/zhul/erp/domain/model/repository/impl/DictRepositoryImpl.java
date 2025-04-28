package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.Dict;
import com.zhul.erp.domain.model.mapper.DictMapper;
import com.zhul.erp.domain.model.repository.IDictRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 字典表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class DictRepositoryImpl extends
    ServiceImpl<DictMapper, Dict> implements
    IDictRepository {

}
