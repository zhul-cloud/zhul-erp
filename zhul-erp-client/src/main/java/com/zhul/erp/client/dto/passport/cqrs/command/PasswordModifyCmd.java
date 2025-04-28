package com.zhul.erp.client.dto.passport.cqrs.command;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Created by yanglikai on 2023/12/16.
 */
@Data
public class PasswordModifyCmd implements Serializable {

  @NotNull(message = "用户编号为必填项")
  @ApiModelProperty(value = "用户编号", required = true)
  private Integer userId;

  @NotBlank(message = "旧密码为必填项")
  @ApiModelProperty(value = "旧密码", required = true)
  private String oldPassword;

  @Length(min = 8, max = 16, message = "密码长度{min}~{max}位，数字、字母、字符至少包含两种")
  @NotBlank(message = "新密码为必填项")
  @ApiModelProperty(value = "新密码", required = true)
  private String newPassword;
}
