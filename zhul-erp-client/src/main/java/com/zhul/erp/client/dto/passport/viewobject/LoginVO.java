package com.zhul.erp.client.dto.passport.viewobject;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/16.
 */
@Data
public class LoginVO implements Serializable {

  @ApiModelProperty(value = "访问Token")
  private String accessToken;

  @ApiModelProperty(value = "用户编号")
  private Integer userId;

  @ApiModelProperty(value = "用户昵称")
  private String nickname;
}
