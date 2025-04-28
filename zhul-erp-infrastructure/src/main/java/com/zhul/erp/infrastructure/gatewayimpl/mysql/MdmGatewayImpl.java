package com.zhul.erp.infrastructure.gatewayimpl.mysql;

import com.google.common.collect.Lists;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.domain.gateway.IMdmGateway;
import com.zhul.erp.domain.model.entity.Department;
import com.zhul.erp.domain.model.entity.Role;
import com.zhul.erp.domain.model.entity.UserBasic;
import com.zhul.erp.domain.model.repository.IDepartmentRepository;
import com.zhul.erp.domain.model.repository.IRoleRepository;
import com.zhul.erp.domain.model.repository.IUserBasicRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yanglikai on 2023/12/19.
 */
@Component
public class MdmGatewayImpl implements IMdmGateway {

  @Autowired
  private IDepartmentRepository departmentRepository;

  @Autowired
  private IRoleRepository roleRepository;

  @Autowired
  private IUserBasicRepository userBasicRepository;

  @Override
  public List<Keyvalue> loadAll4Dept() {

    List<Department> all = this.departmentRepository.lambdaQuery().list();

    return all.stream().map(el -> new Keyvalue(String.valueOf(el.getId()), el.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Keyvalue> loadAll4Role() {

    List<Role> all = this.roleRepository.lambdaQuery().list();

    return all.stream().map(el -> new Keyvalue(el.getCode(), el.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Keyvalue> loadAll4User() {

    List<UserBasic> all = this.userBasicRepository.lambdaQuery().list();

    return all.stream().map(el -> new Keyvalue(String.valueOf(el.getId()), el.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public List<String> loadRoleCodes(List<Integer> ids) {

    List<Role> list = this.roleRepository.lambdaQuery().in(Role::getId, ids).list();

    return list.stream().map(el -> el.getCode()).collect(Collectors.toList());
  }

  @Override
  public List<String> loadOrgCodes(List<Integer> ids) {

    if (ids == null) {
      return Lists.newArrayList();
    }

    return Lists.newArrayList();
  }
}
