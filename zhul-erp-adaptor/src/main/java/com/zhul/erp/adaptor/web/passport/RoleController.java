package com.zhul.erp.adaptor.web.passport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.erp.client.api.passport.IRoleService;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.RoleDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.RoleListQry;
import com.zhul.erp.client.dto.passport.viewobject.RoleDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.RoleListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * Created by yanglikai on 2023/12/17.
 */
@Slf4j
@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/web/")
public class RoleController {

  @Autowired
  private IRoleService roleService;

  @ApiOperation(value = "新增角色")
  @PostMapping(value = "/v1/passport/roles", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse add(@Valid @RequestBody RoleAddCmd cmd) {

    this.roleService.add(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "删除角色")
  @DeleteMapping(value = "/v1/passport/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse delete(@PathVariable("id") Integer id, @Valid @RequestBody RoleDeleteCmd cmd) {

    cmd.setId(id);

    this.roleService.delete(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "修改角色")
  @PutMapping(value = "/v1/passport/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse modify(@PathVariable("id") Integer id, @Valid @RequestBody RoleModifyCmd cmd) {

    this.roleService.modify(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "角色列表")
  @GetMapping(value = "/v1/passport/roles", produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<RoleListVO> list(@Valid RoleListQry qry) {

    Page<RoleListVO> vo = this.roleService.list(qry);

    return vo;
  }

  @ApiOperation(value = "角色详情")
  @GetMapping(value = "/v1/passport/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public RoleDtlVO dtl(@PathVariable("id") Integer id, @Valid RoleDtlQry qry) {

    qry.setId(id);

    RoleDtlVO vo = this.roleService.dtl(qry);

    return vo;
  }
}
