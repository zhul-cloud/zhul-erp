package com.zhul.erp.application.cqrs.passport.model;

import com.zhul.erp.client.dto.passport.viewobject.LoginVO;
import com.zhul.erp.domain.model.aggregate.User;
import java.io.Serializable;
import java.util.stream.Stream;
import lombok.Data;

/**
 * Created by yanglikai on 2024/1/4.
 */
@Data
public class LoginMapper implements Serializable {

  private User user;

  public LoginMapper(User user) {
    this.user = user;
  }

  public LoginVO mapToVO() {
    return Stream.of(this.user).map(el -> {
      LoginVO vo = new LoginVO();
      vo.setAccessToken(el.getAccount().token());
      vo.setUserId(el.userId());
      vo.setNickname(el.name());

      return vo;
    }).findFirst().get();
  }
}
