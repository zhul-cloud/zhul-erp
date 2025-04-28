package com.zhul.erp.adaptor.web.passport;

import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.erp.client.api.passport.IResourceService;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.ResourceDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.ResourceListQry;
import com.zhul.erp.client.dto.passport.viewobject.ResourceDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.ResourceListVO;
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
@Api(tags = "资源管理")
@RestController
@RequestMapping("/api/web/")
public class ResourceController {

  @Autowired
  private IResourceService resourceService;

  @ApiOperation(value = "新增资源")
  @PostMapping(value = "/v1/passport/resources", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse add(@Valid @RequestBody ResourceAddCmd cmd) {

    this.resourceService.add(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "删除资源")
  @DeleteMapping(value = "/v1/passport/resources/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse delete(@PathVariable("id") Integer id,
      @Valid @RequestBody ResourceDeleteCmd cmd) {

    cmd.setId(id);

    this.resourceService.delete(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "修改资源")
  @PutMapping(value = "/v1/passport/resources/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse modify(@PathVariable("id") Integer id,
      @Valid @RequestBody ResourceModifyCmd cmd) {

    this.resourceService.modify(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "资源列表")
  @GetMapping(value = "/v1/passport/resources", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ResourceListVO> list(@Valid ResourceListQry qry) {

    List<ResourceListVO> vo = this.resourceService.list(qry);

    return vo;
  }

  @ApiOperation(value = "资源详情")
  @GetMapping(value = "/v1/passport/resources/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResourceDtlVO dtl(@PathVariable("id") Integer id, @Valid ResourceDtlQry qry) {

    qry.setId(id);

    ResourceDtlVO vo = this.resourceService.dtl(qry);

    return vo;
  }

  @ApiOperation(value = "资源树")
  @GetMapping(value = "/v1/passport/resources/tree", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ResourceListVO> tree() {

    ResourceListQry qry = new ResourceListQry();
    qry.setStatus(1);

    List<ResourceListVO> vo = this.resourceService.list(qry);

    return vo;
  }
}
