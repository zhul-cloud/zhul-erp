package com.zhul.erp.client.dto.passport.cqrs.command;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class ResourceAddCmd implements Serializable {

  @ApiModelProperty(value = "父ID")
  private Integer pid;

  @NotNull(message = "资源类型为必填项")
  @ApiModelProperty(value = "资源类型", example = "1-目录、2-菜单、3-按钮、4-字段）", required = true)
  private Integer type;

  @ApiModelProperty(value = "资源编码", example = "只有手动添加字段类型资源时填写")
  private String code;

  @Length(max = 32, message = "资源名称长度不超过{max}位")
  @NotBlank(message = "资源名称为必填项")
  @ApiModelProperty(value = "资源名称", required = true)
  private String name;

  @Length(max = 32, message = "资源英文名称长度不超过{max}位")
  @NotBlank(message = "资源英文名称为必填项")
  @ApiModelProperty(value = "英文名称", required = true)
  private String englishName;


  @Length(max = 164, message = "路径长度不超过{max}位")
  @NotBlank(message = "路劲为必填项")
  @ApiModelProperty(value = "路径", required = true)
  private String path;

  @NotNull(message = "状态为必填项")
  @ApiModelProperty(value = "状态", example = "0-禁用、1-启用", required = true)
  private Integer status;

  @Min(value = -99999999, message = "排序不能小于-99999999")
  @Max(value = 99999999, message = "排序不能大于99999999")
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
}
