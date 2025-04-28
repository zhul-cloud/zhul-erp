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
public class RoleListVO implements Serializable {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "角色编码")
  private String code;

  @ApiModelProperty(value = "角色名称")
  private String name;

  @ApiModelProperty(value = "角色描述")
  private String remark;

  @ApiModelProperty(value = "数据权限范围", example = "0-无权限、1-全部数据、2-自定义数据、3-仅本人数据")
  private Integer permissionScope;

  @ApiModelProperty(value = "角色状态", example = "0-禁用、1-启用")
  private Integer status;

  @ApiModelProperty(value = "角色状态描述")
  private String statusDesc;

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
