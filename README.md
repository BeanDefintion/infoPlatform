# infoPlatform
基于SpringCloud的一个小实践项目

1.目前已使用的SpringCloud组件有:

Eureka GateWay OpenFeign Bus Config Netflix

2.目前已使用的技术有:

Redis Rabbitmq MybatisPlus Docker

3.目标:

能够迭代出一个目前最新版本的可用的SpringCloud通用框架 一方面实现自己提升 一方面为了以后的方便

4.目前未解决的问题:

1.最新版本的GateWay的统一异常捕捉处理,类似与@controllerAdvice(版本切换成Greenwich.RELEASE,成功用重定义errorWebExceptionHandle
的方式实现了)
2.配置的getway限流一直不生效,发现他前面还有一个filter,测试的连接被它转走了
3.gateway默认的限流配置是通过reactiveRedis完成的,我进入了它的源码, RedisRateLimiter是通过execute目录下的lua脚本来执行限流的操作的.
然后我发现自己的配置怎么都不生效,设置尝试了运行lua文件,最后在它的issue里面发现可能是版本的问题.
微软编译的redis版本只到了3.

