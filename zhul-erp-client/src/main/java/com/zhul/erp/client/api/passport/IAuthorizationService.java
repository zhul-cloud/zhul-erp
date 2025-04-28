package com.zhul.erp.client.api.passport;

import com.zhul.erp.client.dto.passport.viewobject.ResourceListVO;
import com.zhul.erp.client.dto.passport.viewobject.UserInfoVO;
import java.util.List;

/**
 * Created by yanglikai on 2024/1/8.
 */
public interface IAuthorizationService {

  /**
   * 用户信息
   * <p>
   * 用户登录后信息
   * </p>
   *
   * @return
   */
  UserInfoVO userInfo();

  /**
   * 菜单信息
   * <p>
   * 用户授权菜单信息
   * </p>
   *
   * @return
   */
  List<ResourceListVO> resources();
}
