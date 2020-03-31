# 正向代理&反向代理
反向路由或者代理(reverse proxy)，主要针对前向路由和代理(forward proxy)来讲的，都是技术术语。

举例，如果我们X要访问国外的一个网站Z，但是被墙了，这可以时候可以通过找代理Y来翻墙访问，这个代理就是前向代理，它帮助我们去访问目标服务（这个目标服务我们是知道的，只是被墙了），X->Y->Z。

对于企业内部的服务，一般躲在如nginx这样的反向代理后面，当我们X去访问企业的服务Z时候，我们并不知道具体服务部署在那里，反向代理Y知道并且会反向帮我们去转发，X->Y->Z这就是反向代理的作用，屏蔽内部服务的部署和实现细节。

两者的地位有点类似，但是具体作用不同。可以进一步参考stackoverflow上的详细解释：https://stackoverflow.com/questions/224664/difference-between-proxy-server-and-reverse-proxy-server


# 服务发现的三种机制
- load balance 独立部署 效率低 耗时
- load balance 在进程内 多语言  缺点：每种语言开发一个版本
- load balance 单机 另外个进程


# 网关
路由，认证安全，监控，限流熔断，审计等跨横切面功能

# pull & push 优缺点
pull 优点一定能pull到，但有延迟，即使有网络抖动，下次也会拉到
push 保证实时，但是有网络抖动，就不会更新到。
