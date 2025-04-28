package com.zhul.erp.domain.model.entity;

import com.zhul.cloud.common.model.GeneralDO;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/25.
 */
@Data
public class Org extends GeneralDO {

  private Integer id;

  private String code;

  private String name;
}
