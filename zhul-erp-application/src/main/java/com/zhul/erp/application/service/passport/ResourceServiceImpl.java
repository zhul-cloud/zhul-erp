package com.zhul.erp.application.service.passport;

import com.zhul.cloud.common.mapper.Mapper;
import com.zhul.erp.client.api.passport.IResourceService;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.ResourceDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.ResourceListQry;
import com.zhul.erp.client.dto.passport.viewobject.ResourceDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.ResourceListVO;
import com.zhul.erp.domain.model.aggregate.ResourceTree;
import com.zhul.erp.domain.model.entity.Resource;
import com.zhul.erp.domain.model.repository.IResourceRepository;
import com.zhul.erp.domain.model.value.ResourceTypeEnum;
import com.zhul.erp.domain.model.value.StatusEnum;
import com.zhul.erp.domain.service.IResourceDomainService;
import com.zhul.erp.domain.spec.ResourceCreateSpec;
import com.zhul.erp.domain.spec.ResourceModifySpec;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Service
public class ResourceServiceImpl implements IResourceService {

  @Autowired
  private IResourceDomainService resourceDomainService;

  @Autowired
  private IResourceRepository resourceRepository;

  /**
   * 新增资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean add(ResourceAddCmd cmd) {

    // 1.创建资源
    Resource resource = this.resourceDomainService.create(cmd);

    // 2.规则效验
    new ResourceCreateSpec().satisfiedBy(resource);

    // 3.数据持久化
    this.resourceRepository.save(resource);

    return true;
  }

  /**
   * 删除资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean delete(ResourceDeleteCmd cmd) {

    this.resourceRepository.removeById(cmd.getId());

    return true;
  }

  /**
   * 修改资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean modify(ResourceModifyCmd cmd) {

    // 1.更新资源
    Resource resource = this.resourceDomainService.update(cmd);

    // 2.规则效验
    new ResourceModifySpec().satisfiedBy(resource);

    // 3.数据持久化
    this.resourceRepository.updateById(resource);

    return true;
  }

  /**
   * 资源列表
   * <p></p>
   *
   * @param qry
   * @return
   */
  @Override
  public List<ResourceListVO> list(ResourceListQry qry) {

    // 1.参数解析
    String type = StringUtils.isBlank(qry.getType()) ? "" : qry.getType();

    // 2.数据查询
    List<Resource> resources = this.resourceRepository.lambdaQuery()
        .in(StringUtils.isNotBlank(type), Resource::getType, type.split(","))
        .eq(qry.getStatus() != null, Resource::getStatus, qry.getStatus())
        .orderByAsc(Resource::getPid, Resource::getSort, Resource::getId).list();

    // 3.数据预处理
    resources.forEach(resource -> {
      resource.setTypeName(ResourceTypeEnum.transform(resource.getType()).desc());
      resource.setStatusName(StatusEnum.transform(resource.getStatus()).desc());
    });

    // 4.构建资源树
    List<Resource> tree = new ResourceTree(resources).buildTree();

    // 5.结果转换
    return Mapper.map(tree, ResourceListVO.class);
  }

  /**
   * 资源详情
   * <p></p>
   *
   * @param qry
   * @return
   */
  @Override
  public ResourceDtlVO dtl(ResourceDtlQry qry) {

    Resource resource = this.resourceRepository.getById(qry.getId());

    return Mapper.map(resource, ResourceDtlVO.class);
  }
}
