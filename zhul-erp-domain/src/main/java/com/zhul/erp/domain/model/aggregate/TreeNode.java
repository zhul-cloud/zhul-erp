package com.zhul.erp.domain.model.aggregate;

import java.util.List;

/**
 * Created by yanglikai on 2023/12/20.
 */
public interface TreeNode<T> {

  Integer getPid();

  Integer getId();

  void setChildren(List<T> target);
}
