package com.zhul.erp.domain.model.aggregate;

import cn.hutool.core.collection.CollectionUtil;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.domain.model.entity.Org;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * 数据权限
 * <p></p>
 * Created by yanglikai on 2023/12/18.
 */
@Data
public class DataPermission implements Serializable {

  private List<Org> orgs;

  public DataPermission(List<Org> orgs) {
    this.orgs = orgs;
  }

  public boolean hasOrgs() {
    return CollectionUtil.isNotEmpty(this.orgs);
  }

  public static DataPermission create4Orgs(List<String> orgs) {
    return new DataPermission(orgs.stream().map(code -> {
      Org org = new Org();
      org.setCode(code);
      return org;
    }).collect(Collectors.toList()));
  }

  public List<Keyvalue> formatToKeyvalue() {
    return this.orgs.stream().map(el -> new Keyvalue(el.getCode(), el.getName()))
        .collect(Collectors.toList());
  }

  public List<Integer> formatToInteger() {
    return this.orgs.stream().map(el -> el.getId()).collect(Collectors.toList());
  }

  public List<String> formatToString() {
    return this.orgs.stream().map(el -> el.getCode()).collect(Collectors.toList());
  }
}
