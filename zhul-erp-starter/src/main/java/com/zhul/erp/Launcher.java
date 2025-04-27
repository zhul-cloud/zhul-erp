package com.zhul.erp;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;

@EnableZhulService
public class Launcher {

  public static void main(String[] args) {
    SpringApplication.run(Launcher.class, args);
  }
}
