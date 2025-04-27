## 介绍
 + zhul-erp-项目框架

##  模块
 + [adaptor](zhul-erp-adaptor/README.md): 接口适配层，负责兼容适配各自接入端接口。
 + [application](zhul-erp-application/README.md): 应用层，负责领域的组合、编排、转发、转换和传递。
 + [domain](zhul-erp-domain/README.md): 领域层，核心业务领域模型。
 + [infrastructure](zhul-erp-infrastructure/README.md): 基础设施层，为业务提供支撑能力。
 + [client](zhul-erp-client/README.md): 客户端层，包含服务对外提供的接口。
 + [starter](zhul-erp-starter/README.md): 启动器，应用的启动入口。
 + [generator](zhul-erp-generator/README.md): 代码生成器，根据表结构生成对应的实体。

 ## 如何构建

 ### 1.编译
 ```
 mvn clean install -DskipTests
 ```

## 接入前准备

1、接入前需先部署Nacos配置中心和注册中心

2、在配置中心需配置数据库相关配置

## 如何使用

 ### 1.执行sql脚本
 按顺序执行如下脚本：
 + [00-schema.sql](Documents/dev_project/project_wanq/zhul-erp/sql/00-schema.sql)
 + [01-data.sql](Documents/dev_project/project_wanq/zhul-erp/sql/01-data.sql)

 ### 2.修改数据库配置
 1.代码自动生成-数据库配置
 + [application.properties](zhul-erp-generator/src/main/resources/application.properties)

 ```
sp.url=jdbc:mysql://127.0.0.1:3306/xxxxxx?useUnicode=true&serverTimezone=UTC&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull&&useSSL=false
sp.username=xxxxxx
sp.password=xxxxxx
 ```

 ### 3.代码生成
 执行如下程序：
  + [代码自动生成](zhul-erp-generator/src/main/java/com/zhul/erp/generator/Client.java) 

 ### 4.应用启动
  启动应用，支持IDE直接启动和编译打包启动。
  + [应用启动](zhul-erp-provider/src/main/java/com/zhul/erp/Launcher.java)

 ### 5.接口验证
  + 访问http://localhost:8010/v1/users/{id}/