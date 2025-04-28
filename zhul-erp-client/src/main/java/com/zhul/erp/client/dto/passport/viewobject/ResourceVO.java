package com.zhul.erp.client.dto.passport.viewobject;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/16.
 */
@Data
public class ResourceVO implements Serializable {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "父ID")
  private Integer pid;

  @ApiModelProperty(value = "类型(1-目录，2-菜单，3-按钮，4-字段）)")
  private Integer type;

  @ApiModelProperty(value = "编码")
  private String code;

  @ApiModelProperty(value = "名称")
  private String name;

  @ApiModelProperty(value = "排序")
  private Integer sort;

  @ApiModelProperty(value = "浅色默认图标地址")
  private String lightIcon;

  @ApiModelProperty(value = "浅色选中状态图标地址")
  private String lightSelectedIcon;

  @ApiModelProperty(value = "深色默认图标地址")
  private String darkIcon;

  @ApiModelProperty(value = "深色选中状态图标地址")
  private String darkSelectedIcon;

  @ApiModelProperty(value = "路径")
  private String path;

  @ApiModelProperty(value = "状态（0-禁用，1-启用）")
  private Integer status;
}
