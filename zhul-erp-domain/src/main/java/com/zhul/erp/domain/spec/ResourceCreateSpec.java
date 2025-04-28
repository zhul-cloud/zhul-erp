package com.zhul.erp.domain.spec;

import com.zhul.cloud.common.spec.ISpecification;
import com.zhul.cloud.common.spec.Notification;
import com.zhul.erp.domain.model.entity.Resource;
import javax.validation.constraints.NotNull;

/**
 * Created by yanglikai on 2023/12/16.
 */
public class ResourceCreateSpec implements ISpecification<Resource> {

  @Override
  public boolean satisfiedBy(@NotNull Resource resource) {
    return true;
  }

  @Override
  public boolean satisfiedBy(@NotNull Resource resource, Notification notification) {
    return true;
  }
}
