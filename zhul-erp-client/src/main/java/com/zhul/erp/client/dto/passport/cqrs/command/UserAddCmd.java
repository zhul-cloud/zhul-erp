package com.zhul.erp.client.dto.passport.cqrs.command;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class UserAddCmd implements Serializable {

  @NotNull(message = "用户类型为必填项")
  @ApiModelProperty(value = "用户类型", example = "1-员工", required = true)
  private Integer type;

  @Length(max = 32, message = "姓名长度不超过{max}位")
  @NotBlank(message = "姓名为必填项")
  @ApiModelProperty(value = "姓名", required = true)
  private String name;

  @Length(min = 4, max = 20, message = "用户名长度{min}-{max}位")
  @NotBlank(message = "用户名为必填项")
  @ApiModelProperty(value = "用户名", required = true)
  private String username;

  @ApiModelProperty(value = "用户密码")
  private String password;

  @Length(max = 11, message = "手机号长度不超过{max}位")
  @NotBlank(message = "手机号为必填项")
  @ApiModelProperty(value = "手机号", required = true)
  private String phone;

  @NotNull(message = "所属部门为必填项")
  @ApiModelProperty(value = "所属部门编号", required = true)
  private Integer deptId;

  @NotNull(message = "状态为必填项")
  @ApiModelProperty(value = "状态", example = "0-禁用、1-启用", required = true)
  private Integer status;

  @ApiModelProperty(value = "上级领导ID")
  private Integer pid;

  @NotEmpty(message = "角色为必填项")
  @ApiModelProperty(value = "用户角色编号列表", required = true)
  private List<Integer> roleIds;

  @ApiModelProperty(value = "用户机构编号列表", required = true)
  private List<Integer> orgIds;
}
