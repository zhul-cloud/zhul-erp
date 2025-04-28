package com.zhul.erp.adaptor.web.passport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.erp.client.api.passport.IDictItemService;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.DictItemDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.DictItemListQry;
import com.zhul.erp.client.dto.passport.viewobject.DictItemDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.DictItemListVO;
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
 * Created by yanglikai on 2023/11/5.
 */
@Slf4j
@Api(tags = "字典项管理")
@RestController
@RequestMapping("/api/web/")
public class DictItemController {

  @Autowired
  private IDictItemService dictItemService;

  @ApiOperation(value = "新增字典项")
  @PostMapping(value = "/v1/passport/dicts/{pid}/items", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse add(@PathVariable(value = "pid") Integer pid,
      @Valid @RequestBody DictItemAddCmd cmd) {

    cmd.setPid(pid);

    this.dictItemService.add(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "删除字典项")
  @DeleteMapping(value = "/v1/passport/dicts/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse delete(@PathVariable("id") Integer id,
      @Valid @RequestBody DictItemDeleteCmd cmd) {

    cmd.setId(id);

    this.dictItemService.delete(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "修改字典项")
  @PutMapping(value = "/v1/passport/dicts/{pid}/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse modify(@PathVariable(value = "pid") Integer pid,
      @PathVariable("id") Integer id,
      @Valid @RequestBody DictItemModifyCmd cmd) {

    cmd.setPid(pid);

    this.dictItemService.modify(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "字典项列表")
  @GetMapping(value = "/v1/passport/dicts/{pid}/items", produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<DictItemListVO> list(@PathVariable(value = "pid") Integer pid,
      @Valid DictItemListQry qry) {

    qry.setPid(pid);

    Page<DictItemListVO> vo = this.dictItemService.list(qry);

    return vo;
  }

  @ApiOperation(value = "字典项详情")
  @GetMapping(value = "/v1/passport/dicts/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DictItemDtlVO dtl(@PathVariable("id") Integer id, @Valid DictItemDtlQry qry) {

    qry.setId(id);

    DictItemDtlVO vo = this.dictItemService.dtl(qry);

    return vo;
  }
}
