## 介绍
 + zhul-erp-项目框架-用户接口层
    + 一般包括用户接口、Web 服务、rpc请求，mq消息等外部输入均被视为外部输入的请求，对外暴露API，具体形式不限于RPC、Rest API、消息等。
    + 一般都很薄，提供必要的参数校验和异常捕获流程。
    + 一般会提供VO或者DTO到Entity或者ValueObject的转换，用于前后端调用的适配，当然dto可以直接使用command和query，视情况而定。
    + 用户接口层很重要，在于前后端调用的适配。若你的微服务要面向很多应用或渠道提供服务，而每个渠道的入参出参都不一样，你不太可能开发出太多应用服务，这样Facade接口就起很好的作用了，包括DO和DTO对象的组装和转换等。

## 版本历史
 + 1.0.0 初始版本

## 包说明
 + h5: 小程序接口
 + mobile: 移动端接口
 + mq: 消息接口
 + rpc：远程调用接口
 + scheduler：定时任务接口
 + web：后台管理接口