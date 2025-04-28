package com.zhul.erp.client.dto.passport.cqrs.command;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/16.
 */
@Data
public class SignupCmd implements Serializable {

  @NotBlank(message = "用户名为必填项")
  @ApiModelProperty(value = "用户名", required = true)
  private String username;

  @NotBlank(message = "密码为必填项")
  @ApiModelProperty(value = "密码", required = true)
  private String password;

  @ApiModelProperty(value = "手机号")
  private String phone;

  @ApiModelProperty(value = "邮箱地址")
  private String email;
}
