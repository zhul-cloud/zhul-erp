package com.zhul.erp.client.dto.passport.viewobject;

import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.cloud.common.model.RootObject;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class RoleDtlVO extends RootObject {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "角色编码")
  private String code;

  @ApiModelProperty(value = "角色名称")
  private String name;

  @ApiModelProperty(value = "角色状态", example = "0-禁用、1-启用")
  private Integer status;

  @ApiModelProperty(value = "角色描述")
  private String remark;

  @ApiModelProperty(value = "数据权限范围", example = "0-无权限、1-全部数据、2-自定义数据、3-仅本人数据")
  private Integer permissionScope;

  @ApiModelProperty(value = "仓库列表")
  private List<Keyvalue> warehouses;

  @ApiModelProperty(value = "菜单列表", example = "只有目录和菜单")
  private List<Integer> resources;
}
