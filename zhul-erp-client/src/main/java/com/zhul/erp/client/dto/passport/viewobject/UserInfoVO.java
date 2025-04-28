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
public class UserInfoVO extends RootObject {

  @ApiModelProperty(value = "用户编号")
  private Integer userId;

  @ApiModelProperty(value = "用户姓名")
  private String name;

  @ApiModelProperty(value = "用户昵称")
  private String nickName;

  @ApiModelProperty(value = "角色")
  private Keyvalue role;

  @ApiModelProperty(value = "资源授权列表")
  private List<ResourceListVO> resources;
}
