package com.zhul.erp.client.dto.passport.viewobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class UserListVO implements Serializable {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "上级领导编号")
  private Integer pid;

  @ApiModelProperty(value = "上级领导名称")
  private String pname;

  @ApiModelProperty(value = "部门编号")
  private String deptId;

  @ApiModelProperty(value = "部门名称")
  private String deptName;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "用户名")
  private String username;

  @ApiModelProperty(value = "手机号")
  private String phone;

  @ApiModelProperty(value = "状态", example = "0-禁用、1-启用")
  private Integer status;

  @ApiModelProperty(value = "状态名称")
  private String statusName;

  @ApiModelProperty(value = "角色编码")
  private String roleCode;

  @ApiModelProperty(value = "角色名称")
  private String roleName;

  @ApiModelProperty(value = "创建人")
  private String createBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "创建时间")
  private Date createTime;

  @ApiModelProperty(value = "修改人")
  private String updateBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "修改时间")
  private Date updateTime;
}
