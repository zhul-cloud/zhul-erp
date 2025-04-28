package com.zhul.erp.client.dto.passport.viewobject;

import com.zhul.cloud.common.model.RootObject;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class UserDtlVO extends RootObject {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "上级领导ID")
  private Integer pid;

  @ApiModelProperty(value = "上级领导")
  private String pname;

  @ApiModelProperty(value = "用户姓名")
  private String name;

  @ApiModelProperty(value = "用户名")
  private String username;

  @ApiModelProperty(value = "用户类型", example = "1-员工")
  private Integer type;

  @ApiModelProperty(value = "所属部门编号")
  private Integer deptId;

  @ApiModelProperty(value = "所属部门名称")
  private String deptName;

  @ApiModelProperty(value = "手机号")
  private String phone;

  @ApiModelProperty(value = "状态", example = "0-禁用、1-启用、9-注销")
  private Integer status;

  @ApiModelProperty(value = "用户角色编号列表")
  private List<Integer> roleIds;

  @ApiModelProperty(value = "用户机构编号列表")
  private List<Integer> orgIds;
}
