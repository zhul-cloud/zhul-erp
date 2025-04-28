package com.zhul.erp.infrastructure.config;

import cn.hutool.json.JSONUtil;
import com.zhul.cloud.common.exception.BizException;
import com.zhul.erp.domain.manager.UserManager;
import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.entity.AccountAccessToken;
import com.zhul.erp.domain.model.repository.IAccountAccessTokenRepository;
import com.zhul.erp.domain.model.value.ErrorMsg;
import com.zhul.erp.domain.service.IUserDomainService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Created by yanglikai on 2024/01/05.
 */
@Slf4j
@Component
public class UserAuthInterceptor implements HandlerInterceptor {

  private IAccountAccessTokenRepository accessTokenRepository;

  private IUserDomainService userDomainService;

  public UserAuthInterceptor(IAccountAccessTokenRepository accessTokenRepository,
      IUserDomainService userDomainService) {
    this.accessTokenRepository = accessTokenRepository;
    this.userDomainService = userDomainService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    // 从authorizationHeader中提取Bearer token
    String authorizationHeader = request.getHeader("Authorization");
    //语言，后续使用
    String language = request.getHeader("language");

    if (StringUtils.isBlank(authorizationHeader)) {
      throw new BizException(ErrorMsg.D0008.code(), ErrorMsg.D0008.msg());
    }

    String token = authorizationHeader.substring(7);

    // Token效验
    AccountAccessToken accountAccessToken = this.accessTokenRepository.lambdaQuery()
        .eq(AccountAccessToken::getAccessToken, token).one();
    if (accountAccessToken == null) {
      throw new BizException(ErrorMsg.D0007.code(), ErrorMsg.D0007.msg());
    }

    // 设置上下文
    User user = this.userDomainService.queryByUserId(accountAccessToken.getUserId());
    if (user == null) {
      throw new BizException(ErrorMsg.D0007.code(), ErrorMsg.D0007.msg());
    }

    UserManager.setUser(user);
    UserManager.setLanguage(language);

    log.info("用户上下文：" + JSONUtil.toJsonStr(user));

    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    UserManager.removeUser();
    UserManager.removeLanguages();
  }

}
