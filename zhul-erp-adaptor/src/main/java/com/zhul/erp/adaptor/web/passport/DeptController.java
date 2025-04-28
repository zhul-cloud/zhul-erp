package com.zhul.erp.adaptor.web.passport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.erp.domain.model.aggregate.TreeMapper;
import com.zhul.erp.domain.model.entity.Department;
import com.zhul.erp.domain.model.repository.IDepartmentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * Created by yanglikai on 2023/12/20.
 */
@Slf4j
@Api(tags = "部门管理")
@RestController
@RequestMapping("/api/web/")
public class DeptController {

  @Autowired
  private IDepartmentRepository departmentRepository;

  @ApiOperation(value = "新增部门")
  @PostMapping(value = "/v1/passport/depts", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse add(@Valid @RequestBody Department department) {

    this.departmentRepository.save(department);

    return new CRUResponse();
  }

  @ApiOperation(value = "删除部门")
  @DeleteMapping(value = "/v1/passport/depts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse delete(@PathVariable("id") Integer id) {

    this.departmentRepository.removeById(id);

    return new CRUResponse();
  }

  @ApiOperation(value = "修改部门")
  @PutMapping(value = "/v1/passport/depts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CRUResponse modify(@PathVariable("id") Integer id,
      @Valid @RequestBody Department department) {

    this.departmentRepository.updateById(department);

    return new CRUResponse();
  }

  @ApiOperation(value = "部门列表")
  @GetMapping(value = "/v1/passport/depts", produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<Department> list(Department department) {

    String code = department.getCode();
    String name = department.getName();
    Integer status = department.getStatus();

    return this.departmentRepository.page(department.tPage(),
        new LambdaQueryWrapper<Department>()
            .eq(StringUtils.isNotBlank(code), Department::getCode, code)
            .eq(StringUtils.isNotBlank(name), Department::getName, name)
            .eq(status != null, Department::getStatus, status)
            .orderByDesc(Department::getCreateTime));
  }

  @ApiOperation(value = "部门详情")
  @GetMapping(value = "/v1/passport/depts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Department dtl(@PathVariable("id") Integer id) {

    return this.departmentRepository.getById(id);
  }

  @ApiOperation(value = "部门树")
  @GetMapping(value = "/v1/passport/depts/tree", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Department> tree(String searchKey) {

    // 1.数据查询
    List<Department> depts = this.departmentRepository.selectBySearchKey(searchKey);

    // 2.构建资源树
    List<Department> tree = new TreeMapper(depts).buildTree();

    // 3.结果换回
    return tree;
  }
}
