package com.zhul.erp.domain.model.repository.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zhul.erp.domain.model.entity.Role;
import com.zhul.erp.domain.model.repository.IResourceRepository;
import com.zhul.erp.domain.model.repository.IRoleRepository;
import com.zhul.erp.domain.model.repository.ISerialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by yanglikai on 2024/1/8.
 */
@Repository
public class SerialRepositoryImpl implements ISerialRepository {

  private final static int ROLE_INCREMENT = 100;

  private final static int REOUSRCE_INCREMENT = 100000;


  @Autowired
  private IRoleRepository roleRepository;

  @Autowired
  private IResourceRepository resourceRepository;

  /**
   * 角色编码
   * <p></p>
   *
   * @return
   */
  @Override
  public String roleCode() {
    //long count = this.roleRepository.count();
    List<Role> roles = roleRepository.lambdaQuery().orderByDesc(Role::getId).list();
    if(CollectionUtil.isEmpty(roles)){
      return String.format("R%s", ROLE_INCREMENT);
    }
    Role role = roles.get(0);
    int roleNumber = Integer.parseInt(role.getCode().replace("R",""));
    return String.format("R%s", roleNumber + 1);
  }

  /**
   * 资源编码
   * <p></p>
   *
   * @return
   */
  @Override
  public String resourceCode() {

    long count = this.resourceRepository.count();

    return String.format("R%s", count + REOUSRCE_INCREMENT);
  }
}
