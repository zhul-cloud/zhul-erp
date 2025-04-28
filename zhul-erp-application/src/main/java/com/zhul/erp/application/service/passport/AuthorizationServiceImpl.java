package com.zhul.erp.application.service.passport;

import com.zhul.cloud.common.mapper.Mapper;
import com.zhul.erp.client.api.passport.IAuthorizationService;
import com.zhul.erp.client.dto.passport.viewobject.ResourceListVO;
import com.zhul.erp.client.dto.passport.viewobject.UserInfoVO;
import com.zhul.erp.domain.manager.UserManager;
import com.zhul.erp.domain.model.aggregate.ResourceTree;
import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.entity.Resource;
import com.zhul.erp.domain.service.IUserDomainService;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2024/1/8.
 */
@Service
public class AuthorizationServiceImpl implements IAuthorizationService {

  @Autowired
  private IUserDomainService userDomainService;

  /**
   * 用户信息
   * <p>
   * 用户登录后信息
   * </p>
   *
   * @return
   */
  @Override
  public UserInfoVO userInfo() {
    // 1.参数解析
    int userId = UserManager.getUserId();

    // 2.查询用户信息
    User user = this.userDomainService.queryByUserId(userId);

    // 3.数据转换
    return Stream.of(user).map(u -> {
      UserInfoVO vo = new UserInfoVO();
      vo.setUserId(u.userId());
      vo.setName(u.name());
      vo.setNickName(u.nickName());
      vo.setRole(u.role());
      vo.setResources(Mapper.map(u.resources(), ResourceListVO.class));

      return vo;
    }).findFirst().get();
  }

  /**
   * 菜单信息
   * <p>
   * 用户授权菜单信息
   * </p>
   *
   * @return
   */
  @Override
  public List<ResourceListVO> resources() {
    // 1.参数解析
    int userId = UserManager.getUserId();

    // 2.查询用户信息
    User user = this.userDomainService.queryByUserId(userId);

    // 3.构建资源树
    List<Resource> tree = new ResourceTree(user.resources()).buildTree();

    // 4.数据转换
    return Mapper.map(tree, ResourceListVO.class);
  }
}
