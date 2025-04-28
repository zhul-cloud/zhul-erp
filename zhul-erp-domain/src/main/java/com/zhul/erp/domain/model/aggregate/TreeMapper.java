package com.zhul.erp.domain.model.aggregate;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * Created by yanglikai on 2023/12/20.
 */
@Data
public class TreeMapper<T> implements Serializable {

  private List<TreeNode<T>> datas;

  public TreeMapper(List<TreeNode<T>> datas) {
    this.datas = datas;
  }

  public List<TreeNode<T>> buildTree() {

    // 构建根节点
    List<TreeNode<T>> rootNodes = this.datas.stream()
        .filter(node -> node.getPid() == null || node.getPid() == 0)
        .collect(Collectors.toList());

    // 递归构建子节点
    rootNodes.forEach(rootNode -> this.buildChildren(rootNode, this.datas));

    return rootNodes;
  }

  private void buildChildren(TreeNode parentNode, List<TreeNode<T>> nodes) {
    List<TreeNode> children = nodes.stream()
        .filter(node -> parentNode.getId().equals(node.getPid()))
        .collect(Collectors.toList());

    // 递归构建子节点的子节点
    children.forEach(child -> this.buildChildren(child, nodes));

    // 将子节点设置到父节点中
    parentNode.setChildren(children);
  }
}
