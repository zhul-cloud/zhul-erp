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
public class DictItemAddCmd implements Serializable {

  @NotNull(message = "字典编号为必填项")
  @ApiModelProperty(value = "字典编号", required = true)
  private Integer pid;

  @Length(max = 64, message = "字典项目名称长度不超过{max}位")
  @NotBlank(message = "字典项目名称为必填项")
  @ApiModelProperty(value = "字典项目名称", required = true)
  private String name;

  @NotBlank(message = "字典值为必填项")
  @ApiModelProperty(value = "字典值", required = true)
  private String value;

  @NotNull(message = "排序为必填项")
  @Min(value = -99999999, message = "排序不能小于-99999999")
  @Max(value = 99999999, message = "排序不能大于99999999")
  @ApiModelProperty(value = "排序", required = true)
  private Integer sort;

  @NotNull(message = "状态为必填项")
  @ApiModelProperty(value = "状态", example = "0-禁用，1-启用", required = true)
  private Integer status;

  @Length(max = 255, message = "备注长度不超过{max}位")
  @ApiModelProperty(value = "备注")
  private String remark;
}
