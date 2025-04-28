package com.zhul.erp.domain.service;


import com.zhul.erp.client.dto.passport.cqrs.command.ResourceAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceModifyCmd;
import com.zhul.erp.domain.model.entity.Resource;

/**
 * Created by yanglikai on 2023/12/19.
 */
public interface IResourceDomainService {

  /**
   * 创建资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Resource create(ResourceAddCmd cmd);

  /**
   * 更新资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Resource update(ResourceModifyCmd cmd);
}
