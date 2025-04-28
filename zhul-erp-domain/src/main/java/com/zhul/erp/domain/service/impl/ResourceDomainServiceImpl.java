package com.zhul.erp.domain.service.impl;

import com.zhul.cloud.common.mapper.Mapper;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceModifyCmd;
import com.zhul.erp.domain.model.entity.Resource;
import com.zhul.erp.domain.model.repository.ISerialRepository;
import com.zhul.erp.domain.service.IResourceDomainService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/19.
 */
@Service
public class ResourceDomainServiceImpl implements IResourceDomainService {

  @Autowired
  private ISerialRepository serialRepository;

  /**
   * 创建资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Resource create(ResourceAddCmd cmd) {

    Resource resource = Mapper.map(cmd, Resource.class);

    if (StringUtils.isBlank(cmd.getCode())) {
      resource.setCode(this.serialRepository.resourceCode());
    }

    return resource;
  }

  /**
   * 更新资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Resource update(ResourceModifyCmd cmd) {

    Resource resource = Mapper.map(cmd, Resource.class);

    return resource;
  }
}
