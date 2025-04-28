package com.zhul.erp.client.dto.passport.viewobject;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class DictItemListVO implements Serializable {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "字典编号")
  private Integer pid;

  @ApiModelProperty(value = "字典项名称")
  private String name;

  @ApiModelProperty(value = "字典值")
  private String value;

  @ApiModelProperty(value = "排序")
  private Integer sort;

  @ApiModelProperty(value = "状态")
  private Integer status;

  @ApiModelProperty(value = "状态名称")
  private String statusName;
}
