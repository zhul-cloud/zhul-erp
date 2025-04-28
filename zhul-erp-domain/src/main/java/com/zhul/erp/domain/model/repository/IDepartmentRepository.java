package com.zhul.erp.domain.model.repository;

import com.zhul.erp.domain.model.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 部门表 Repository 接口
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
public interface IDepartmentRepository extends IService<Department> {

  List<Department> selectAll();

  List<Department> selectBySearchKey(String searchKey);
}
