package com.zhul.erp.infrastructure.config;

import com.zhul.erp.domain.model.repository.IAccountAccessTokenRepository;
import com.zhul.erp.domain.service.IUserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by yanglikai on 2024/01/05.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private IAccountAccessTokenRepository accessTokenRepository;

  @Autowired
  private IUserDomainService userDomainService;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")  // 拦截所有的请求
        .allowedOrigins("*")  // 可跨域的域名，可以为 *
        .allowCredentials(true)
        .allowedMethods("*")   // 允许跨域的方法，可以单独配置
        .allowedHeaders("*");  // 允许跨域的请求头，可以单独配置
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(
        new UserAuthInterceptor(this.accessTokenRepository, this.userDomainService))
        .excludePathPatterns("/api/web/v1/auth/sign-up/**", "/api/web/v1/auth/login/**");
  }
}
