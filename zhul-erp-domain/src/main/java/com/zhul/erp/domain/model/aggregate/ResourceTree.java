package com.zhul.erp.domain.model.aggregate;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.zhul.erp.domain.model.entity.Resource;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/19.
 */
@Data
public class ResourceTree implements Serializable {

  public List<Resource> resources;

  public ResourceTree(List<Resource> resources) {
    this.resources = resources;
  }

  public List<Resource> buildTree() {
    if (CollectionUtil.isEmpty(this.resources)) {
      return Lists.newArrayList();
    }

    // 构建根节点
    List<Resource> rootNodes = this.resources.stream()
            .filter(resource -> resource.getPid() == null || resource.getPid() == 0).sorted(Comparator.comparing(Resource::getSort)).collect(Collectors.toList());

      // 递归构建子节点
    rootNodes.forEach(rootNode -> this.buildChildren(rootNode, this.resources));

    return rootNodes;
  }

  private void buildChildren(Resource parentNode, List<Resource> resourceList) {
    resourceList.sort(Comparator.comparing(Resource::getSort));
    List<Resource> children = resourceList.stream()
        .filter(resource -> parentNode.getId().equals(resource.getPid()))
        .collect(Collectors.toList());

    // 递归构建子节点的子节点
    children.forEach(child -> this.buildChildren(child, resourceList));

    // 将子节点设置到父节点中
    if (CollectionUtil.isNotEmpty(children)) {
      parentNode.setChildren(children);
    }
  }
}
