package com.zhul.erp.adaptor.web.passport;

import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.cloud.common.wrapper.WrapMapper;
import com.zhul.cloud.common.wrapper.Wrapper;
import com.zhul.erp.client.api.passport.IAuthService;
import com.zhul.erp.client.api.passport.IPasswordService;
import com.zhul.erp.client.dto.passport.cqrs.command.LoginCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.PasswordModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.SignupCmd;
import com.zhul.erp.client.dto.passport.viewobject.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2023/12/16.
 */
@Slf4j
@Api(tags = "登录认证管理")
@RestController
@RequestMapping(value = "/api/web")
public class AuthController {

  @Autowired
  private IAuthService authService;

  @Autowired
  private IPasswordService passwordService;

  @ApiOperation(value = "注册")
  @PostMapping(value = "/v1/auth/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse signup(@Valid @RequestBody SignupCmd cmd) {

    this.authService.signup(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "登录")
  @PostMapping(value = "/v1/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public Wrapper<LoginVO> login(@Valid @RequestBody LoginCmd cmd) {

    LoginVO vo = this.authService.login(cmd);

    return WrapMapper.ok(vo);
  }

  @ApiOperation(value = "登出")
  @PostMapping(value = "/v1/auth/logout", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse logout() {

    this.authService.logout();

    return new CRUResponse();
  }

  @ApiOperation(value = "重置密码")
  @PutMapping(value = "/v1/auth/password-reset/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse resetPassword(@PathVariable("userId") Integer userId) {

    this.passwordService.reset(userId);

    return new CRUResponse();
  }

  @ApiOperation(value = "修改密码")
  @PutMapping(value = "/v1/auth/password-mofidy/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse modifyPassword(@PathVariable("userId") Integer userId,
      @Valid @RequestBody PasswordModifyCmd cmd) {

    this.passwordService.modify(cmd);

    return new CRUResponse();
  }
}
