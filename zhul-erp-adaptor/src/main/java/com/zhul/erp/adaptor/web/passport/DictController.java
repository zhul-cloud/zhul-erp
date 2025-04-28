package com.zhul.erp.adaptor.web.passport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.client.api.passport.IDictService;
import com.zhul.erp.client.dto.passport.cqrs.command.DictAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.DictDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.DictListQry;
import com.zhul.erp.client.dto.passport.viewobject.DictDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.DictListVO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2023/11/5.
 */
@Slf4j
@Api(tags = "字典管理")
@RestController
@RequestMapping("/api/web/")
public class DictController {

  @Autowired
  private IDictService dictService;

  @ApiOperation(value = "新增字典")
  @PostMapping(value = "/v1/passport/dicts", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse add(@Valid @RequestBody DictAddCmd cmd) {

    this.dictService.add(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "删除字典")
  @DeleteMapping(value = "/v1/passport/dicts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse delete(@PathVariable("id") Integer id,
      @Valid @RequestBody DictDeleteCmd cmd) {

    cmd.setId(id);

    this.dictService.delete(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "修改字典")
  @PutMapping(value = "/v1/passport/dicts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse modify(@PathVariable("id") Integer id,
      @Valid @RequestBody DictModifyCmd cmd) {

    this.dictService.modify(cmd);

    return new CRUResponse();
  }

  @ApiOperation(value = "字典列表")
  @GetMapping(value = "/v1/passport/dicts", produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<DictListVO> list(@Valid DictListQry qry) {

    Page<DictListVO> vo = this.dictService.list(qry);

    return vo;
  }

  @ApiOperation(value = "字典详情")
  @GetMapping(value = "/v1/passport/dicts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DictDtlVO dtl(@PathVariable("id") Integer id, @Valid DictDtlQry qry) {

    qry.setId(id);

    DictDtlVO vo = this.dictService.dtl(qry);

    return vo;
  }

  @ApiOperation(value = "字典下拉列表")
  @GetMapping(value = "/v1/dropdown-list", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Keyvalue> dropdownList(@RequestParam("code") String code) {
    return this.dictService.dropdownList(code);
  }


}
