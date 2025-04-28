package com.zhul.erp.client.dto.passport.cqrs.command;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class RoleAddCmd implements Serializable {

  @ApiModelProperty(value = "角色编码", example = "支持自定义编码输入，编码为空时，自动生成")
  private String code;

  @Length(max = 32, message = "角色名称长度不超过{max}位")
  @NotBlank(message = "角色名称为必填项")
  @ApiModelProperty(value = "角色名称", required = true)
  private String name;

  @NotNull(message = "角色状态为必填项")
  @ApiModelProperty(value = "角色状态", example = "0-禁用、1-启用", required = true)
  private Integer status;

  @Length(max = 128, message = "角色描述长度不超过{max}位")
  @ApiModelProperty(value = "角色描述")
  private String remark;

  @ApiModelProperty(value = "数据权限范围", example = "0-无权限、1-全部数据、2-自定义数据、3-仅本人数据")
  private Integer permissionScope;

  @ApiModelProperty(value = "仓库列表")
  private List<String> warehouses;

  @ApiModelProperty(value = "菜单列表", example = "只有目录和菜单")
  private List<Integer> resources;
}
