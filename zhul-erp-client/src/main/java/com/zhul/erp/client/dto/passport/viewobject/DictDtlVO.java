package com.zhul.erp.client.dto.passport.viewobject;

import com.zhul.cloud.common.model.RootObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class DictDtlVO extends RootObject {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "字典编码")
  private String code;

  @ApiModelProperty(value = "字典名称")
  private String name;

  @ApiModelProperty(value = "排序")
  private Integer sort;

  @ApiModelProperty(value = "状态", example = "0-禁用，1-启用")
  private Integer status;

  @ApiModelProperty(value = "备注")
  private String remark;
}
