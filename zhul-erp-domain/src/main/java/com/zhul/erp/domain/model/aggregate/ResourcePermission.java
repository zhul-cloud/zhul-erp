package com.zhul.erp.domain.model.aggregate;

import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.domain.model.entity.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * 资源权限
 * <p></p>
 * Created by yanglikai on 2023/12/18.
 */
@Data
public class ResourcePermission implements Serializable {

  private List<Resource> resources;

  public ResourcePermission(List<Resource> resources) {
    this.resources = resources;
  }

  public static ResourcePermission create(List<Integer> resources) {
    return new ResourcePermission(resources.stream().map(id -> {
      Resource resource = new Resource();
      resource.setId(id);
      return resource;
    }).collect(Collectors.toList()));
  }

  public List<Keyvalue> formatToKeyvalue() {
    return this.resources.stream().map(el -> new Keyvalue(el.getCode(), el.getName()))
        .collect(Collectors.toList());
  }

  public List<Integer> formatToInteger() {
    return this.resources.stream().map(el -> el.getId()).collect(Collectors.toList());
  }

  public List<String> formatToString() {
    return this.resources.stream().map(el -> el.getCode()).collect(Collectors.toList());
  }
}
