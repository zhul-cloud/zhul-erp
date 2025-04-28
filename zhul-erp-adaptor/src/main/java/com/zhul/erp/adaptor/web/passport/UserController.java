package com.zhul.erp.adaptor.web.passport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.client.api.passport.IUserService;
import com.zhul.erp.client.dto.passport.cqrs.command.UserAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.UserDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.UserListQry;
import com.zhul.erp.client.dto.passport.viewobject.UserDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.UserListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2023/11/5.
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/web/")
public class UserController {

  @Autowired
  private IUserService userService;

  @ApiOperation(value = "新增用户")
  @PostMapping(value = "/v1/passport/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse add(@Valid @RequestBody UserAddCmd cmd) {

    this.userService.add(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "删除用户")
  @DeleteMapping(value = "/v1/passport/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse delete(@PathVariable("id") Integer id, @Valid @RequestBody UserDeleteCmd cmd) {

    cmd.setId(id);

    this.userService.delete(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "修改用户")
  @PutMapping(value = "/v1/passport/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse modify(@PathVariable("id") Integer id, @Valid @RequestBody UserModifyCmd cmd) {

    this.userService.modify(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "用户列表")
  @GetMapping(value = "/v1/passport/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<UserListVO> list(@Valid UserListQry qry) {

    Page<UserListVO> vo = this.userService.list(qry);

    return vo;
  }

  @ApiOperation(value = "用户详情")
  @GetMapping(value = "/v1/passport/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDtlVO dtl(@PathVariable("id") Integer id, @Valid UserDtlQry qry) {

    qry.setId(id);

    UserDtlVO vo = this.userService.dtl(qry);

    return vo;
  }

  @ApiOperation(value = "用户下拉列表")
  @GetMapping(value = "/v1/passport/users/dropdown-list", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Keyvalue> dropdownList() {

    return this.userService.dropdownList();
  }

  @ApiOperation(value = "用户测试接口")
  @GetMapping(value = "/v1/passport/users/test", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse test() {

    return new CRUResponse();
  }
}
