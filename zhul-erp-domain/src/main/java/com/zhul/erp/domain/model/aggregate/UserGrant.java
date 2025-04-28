package com.zhul.erp.domain.model.aggregate;

import com.zhul.erp.domain.model.entity.Resource;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 用户授权
 * <p></p>
 * Created by yanglikai on 2023/12/25.
 */
@Data
public class UserGrant implements Serializable {

  private User user;

  public UserGrant(User user) {
    this.user = user;
  }

  public String accessToken() {
    return this.user.getAccount().token();
  }

  public Integer userId() {
    return  this.user.userId();
  }

  public String name() {
    return this.user.name();
  }

  public List<Resource> resources() {
    return this.user.resources();
  }
}
