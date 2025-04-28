package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.SysLog;
import com.zhul.erp.domain.model.mapper.SysLogMapper;
import com.zhul.erp.domain.model.repository.ISysLogRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统日志表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class SysLogRepositoryImpl extends
    ServiceImpl<SysLogMapper, SysLog> implements
    ISysLogRepository {

}
