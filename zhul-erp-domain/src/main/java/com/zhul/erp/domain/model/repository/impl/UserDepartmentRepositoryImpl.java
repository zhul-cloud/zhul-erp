package com.zhul.erp.domain.model.repository.impl;

import com.zhul.erp.domain.model.entity.UserDepartment;
import com.zhul.erp.domain.model.mapper.UserDepartmentMapper;
import com.zhul.erp.domain.model.repository.IUserDepartmentRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户部门表 Repository 实现类
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Repository
public class UserDepartmentRepositoryImpl extends
    ServiceImpl<UserDepartmentMapper, UserDepartment> implements
    IUserDepartmentRepository {

}
