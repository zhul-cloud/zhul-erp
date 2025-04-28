package com.zhul.erp.client.dto.passport.cqrs.query;

import com.zhul.cloud.common.model.GeneralQuery;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class UserListQry extends GeneralQuery {

  @ApiModelProperty(value = "用户类型", example = "1-员工")
  private Integer type;

  @ApiModelProperty(value = "所属部门编号")
  private Integer deptId;

  @ApiModelProperty(value = "用户名")
  private String username;

  @ApiModelProperty(value = "用户姓名")
  private String name;

  @ApiModelProperty(value = "手机号")
  private String phone;

  @ApiModelProperty(value = "上级领导ID列表")
  private List<Integer> pids;

  @ApiModelProperty(value = "状态", example = "0-禁用、1-启用")
  private Integer status;
}
