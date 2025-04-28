package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.ChineseAreaCode;
import com.zhul.erp.domain.model.mapper.ChineseAreaCodeMapper;
import com.zhul.erp.domain.model.repository.IChineseAreaCodeRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 中国行政区编码表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class ChineseAreaCodeRepositoryImpl extends
    ServiceImpl<ChineseAreaCodeMapper, ChineseAreaCode> implements
    IChineseAreaCodeRepository {

}
