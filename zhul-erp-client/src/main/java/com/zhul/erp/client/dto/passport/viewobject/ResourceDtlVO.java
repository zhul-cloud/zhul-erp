package com.zhul.erp.client.dto.passport.viewobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhul.cloud.common.model.RootObject;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class ResourceDtlVO extends RootObject {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "父ID")
  private Integer pid;

  @ApiModelProperty(value = "资源类型", example = "1-目录、2-菜单、3-按钮")
  private Integer type;

  @ApiModelProperty(value = "资源类型名称", example = "1-目录、2-菜单、3-按钮")
  private String typeName;

  @ApiModelProperty(value = "资源编码")
  private String code;

  @ApiModelProperty(value = "资源名称")
  private String name;

  @ApiModelProperty(value = "资源英文名称")
  private String englishName;

  @ApiModelProperty(value = "排序")
  private Integer sort;

  @ApiModelProperty(value = "浅色默认图标地址")
  private String lightIcon;

  @ApiModelProperty(value = "深色默认图标地址")
  private String darkIcon;

  @ApiModelProperty(value = "路径")
  private String path;

  @ApiModelProperty(value = "状态", example = "0-禁用、1-启用")
  private Integer status;

  @ApiModelProperty(value = "状态名称", example = "0-禁用、1-启用")
  private String statusName;

  @ApiModelProperty(value = "最后创建人")
  private String createBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "最后创建时间")
  private Date createTime;

  @ApiModelProperty(value = "最后修改人")
  private String updateBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "最后修改时间")
  private Date updateTime;
}
