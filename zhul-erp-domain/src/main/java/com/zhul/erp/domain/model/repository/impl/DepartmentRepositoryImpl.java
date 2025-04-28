package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.Department;
import com.zhul.erp.domain.model.mapper.DepartmentMapper;
import com.zhul.erp.domain.model.repository.IDepartmentRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhul.erp.domain.model.value.StatusEnum;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 部门表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class DepartmentRepositoryImpl extends
    ServiceImpl<DepartmentMapper, Department> implements
    IDepartmentRepository {

  @Override
  public List<Department> selectAll() {
    return this.lambdaQuery().eq(Department::getStatus, StatusEnum.STATUS_1.code()).list();
  }

  @Override
  public List<Department> selectBySearchKey(String searchKey) {
    return this.lambdaQuery().eq(StringUtils.isNotBlank(searchKey), Department::getCode, searchKey)
        .or()
        .like(StringUtils.isNotBlank(searchKey), Department::getName, searchKey)
        .eq(Department::getStatus, StatusEnum.STATUS_1.code())
        .list();
  }
}
