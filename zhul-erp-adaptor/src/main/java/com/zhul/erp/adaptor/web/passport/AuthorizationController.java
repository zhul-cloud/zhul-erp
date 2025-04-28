package com.zhul.erp.adaptor.web.passport;

import cn.hutool.core.collection.CollectionUtil;
import com.zhul.erp.client.api.passport.IAuthorizationService;
import com.zhul.erp.client.dto.passport.viewobject.ResourceListVO;
import com.zhul.erp.client.dto.passport.viewobject.UserInfoVO;
import com.zhul.erp.domain.manager.UserManager;
import com.zhul.erp.domain.model.value.LanguageEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2023/12/16.
 */
@Slf4j
@Api(tags = "用户授权管理")
@RestController
@RequestMapping(value = "/api/web")
public class AuthorizationController {

  @Autowired
  private IAuthorizationService authorizationService;

  @ApiOperation(value = "当前登录用户信息")
  @GetMapping(value = "/v1/passport/authorization/user", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserInfoVO userInfo() {

    UserInfoVO vo = authorizationService.userInfo();

    return vo;
  }

  @ApiOperation(value = "当前登录用户菜单信息")
  @GetMapping(value = "/v1/passport/authorization/resources", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ResourceListVO> userResources() {

    List<ResourceListVO> vos = this.authorizationService.resources();
    String languages = UserManager.getLanguages();
    for (ResourceListVO vo : vos) {
        if (LanguageEnum.US.getDesc().equals(languages)){
          vo.setName(vo.getEnglishName());
        }
      initItem(vo, languages);

    }

    return vos;
  }

  private static void initItem(ResourceListVO vo, String languages) {
    if (CollectionUtil.isNotEmpty(vo.getChildren())){
      for (ResourceListVO child : vo.getChildren()) {
        if (LanguageEnum.US.getDesc().equals(languages)){
          child.setName(child.getEnglishName());
        }
        initItem(child,languages);
      }
    }
  }
}
