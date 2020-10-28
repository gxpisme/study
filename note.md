# 正向代理&反向代理
反向路由或者代理(reverse proxy)，主要针对前向路由和代理(forward proxy)来讲的，都是技术术语。

举例，如果我们X要访问国外的一个网站Z，但是被墙了，这可以时候可以通过找代理Y来翻墙访问，这个代理就是前向代理，它帮助我们去访问目标服务（这个目标服务我们是知道的，只是被墙了），X->Y->Z。

对于企业内部的服务，一般躲在如nginx这样的反向代理后面，当我们X去访问企业的服务Z时候，我们并不知道具体服务部署在那里，反向代理Y知道并且会反向帮我们去转发，X->Y->Z这就是反向代理的作用，屏蔽内部服务的部署和实现细节。

两者的地位有点类似，但是具体作用不同。可以进一步参考stackoverflow上的详细解释：https://stackoverflow.com/questions/224664/difference-between-proxy-server-and-reverse-proxy-server

# 分布式&集群
分布式（distributed）是指在多台不同的服务器中部署不同的服务模块，通过远程调用协同工作，对外提供服务。比如把购物分为商品、购物车、订单、支付。

集群（cluster）是指在多台不同的服务器中部署相同应用或服务模块，构成一个集群，通过负载均衡设备对外提供服务。


[需要翻墙 difference-between-distributed-and-cluster](https://medium.com/@mena.meseha/difference-between-distributed-and-cluster-aca9d50c2c44)
```
Distributed refers to splitting a business into different sub-services and distributing them on different machines. Clustering means that multiple servers are grouped together to achieve the same service. 
```



# 服务发现的三种机制
- load balance 独立部署 效率低 耗时
- load balance 在进程内 多语言  缺点：每种语言开发一个版本
- load balance 单机 另外个进程


# 网关
路由，认证安全，监控，限流熔断，审计等跨横切面功能

# pull & push 优缺点
pull 优点一定能pull到，但有延迟，即使有网络抖动，下次也会拉到。

push 保证实时，但是有网络抖动，就不会更新到。

所以在做服务发现的时候，最好是push pull结合。

# 微服务核心能力
- 服务注册、服务发现
- 软负载 解决流量均匀问题
- 软路由 解决灰度问题
- 日志 方便排查问题
- metrics 打点监控
- 调用链路 错综复杂的系统中，方便排查问题
- 限流熔断 服务保障
- 安全 & 访问控制  
- REST&RPC 两套协议都支持
- 序列化 XML/JSON/binary
- 代码生成
- 异常处理
- 文档 
- 配置集成
- 后台服务集成 DB、Cache、MQ等

# 某台服务优雅上下线
- 采用的是服务注册中心的模式
- `shutdown` 一种是请求还在服务端处理没有写回给客户端，另一种是客户度正准备请求开始重启的服务端。
- `start`    启动阶段，服务还没有完全初始化成功，就有请求进来了。

## 优雅下线
1. 告诉服务注册，我要下线了。这期间可能会有时间延迟，可以`sleep`下。
2. 服务中心，已经下线了。这时候就可以对机器`shutdown`了。

## 优雅上线
1. 先启动，这时候**不要**发送任何注册信息到服务注册中心。
2. 启动成功后，在告诉服务注册中心，我已经`ready`了，可以注册了。

# failover
failover 就是 故障转移。
Fail-Over的含义为“失效转移”，是一种备份操作模式，当主要组件异常时，其功能转移到备份组件。其要点在于有主有备，且主故障时备可启用，并设置为主。如Mysql的双Master模式，当正在使用的Master出现故障时，可以拿备Master做主使用



# InnoDB B+树索引
B+树索引并不能找到一个给定键值的具体行。B+树索引能找到的只是被查找数据行所在的页。然后数据库通过把页读入到内存，再在内存中进行查找，最后得到要查找的数据。

无论对B+树增删，B+树总是会保持平衡。但是为了保持平衡对于新插入的键值可能需要做大量的拆分页split操作。
因为B+树结构主要用于磁盘，页的拆分意外着磁盘的操作，所以应该在可能的情况下尽量减少页的拆分操作。因此，B+树同样提供了类似于平衡二叉树的旋转rotation功能。

B+树索引在数据库中有一个特点是高扇出性，因此在数据库中，B+树的高度一般都在2~4层，这也就是说查找某一键值的行记录时最多需要2到4次IO。因为一般的机械磁盘每秒至少可以做100次IO，2~4次的IO意味着查询时间只需0.02~0.04秒。

# MySQL
二进制日志 bin-log 有三个作用
- 恢复（Recovery） 某些数据的恢复需要二进制日志，例如：在一个数据库全备文件恢复后，用户可以通过二进制日志进行point-in-time的恢复。
- 复制（Replication） 其原理与恢复类似，通过复制和执行二进制日志使一台远程的MySQL数据库（Slave）与一台MySQL（master）进行实时同步。
- 审计（audit）用户可以通过二进制日志的信息来进行审计，判断是否有对数据库进行注入的攻击。



# SLA SLO SLI
```
SLA or Service Level Agreement is a contract that the service provider promises customers on service availability, performance, etc.
服务质量协议  服务提供方对消费者的一种保障协议。

SLO or Service Level Objective is a goal that service provider wants to reach.
服务质量目标  服务提供方想要达到的一个目标

SLI or Service Level Indicator is a measurement the service provider uses for the goal.
服务质量指标 该服务的某项服务质量的一个具体量化指标。常见的SLI 包括错误率（请求处理失败的百分比）、系统吞吐量（每秒请求数量）等。

```
# Concurrency & Parallelism
![](/image/base-knowledge.jpg)


# MySQL 锁
1. X/S 排他锁和共享锁
2. IX/IS 意向排他锁和意向共享锁
3. record 锁记录，拿着索引来锁的
4. gap lock 间隙锁，
  - between index records   两个索引之间
  - before the first record  第一个索引之前
  - after the last index record 最后一个索引之后
5. next lock
  - record + gap lock(before the first record) 该索引和该索引之前的记录
6. Insert intention Locks 插入意向锁
7. Auto-Inc 表级锁

知识点
1. 获取X/S之前必须先获得IX/IS锁
2. 插入意向锁是一种gap lock，如果多个插入到同一个索引间隙的事务，插入的位置不同，则不冲突。
3. 脏读Dirty Read、不可重复度non repeatable、幻读phantom reads
  - 脏读： 读到了其他未提交的数据
  - 不可重复读：读到了别人已经提交的数据，侧重修改数据UPDATE
  - 幻读：读到了别人已经提交的数据，侧重新增数据INSERT/删除数据DELETE

# MySQL 索引
1. InnoDB主键索引、辅助索引， 主键索引就是叶子节点是数据，辅助索引叶子节点是主键。最底层的叶子节点是双链表。覆盖索引，不用走主键索引。
2. MyISAM主键索引、辅助索引， 主键索引就是叶子节点是数据存放的物理位置，辅助索引叶子节点也是存放的物理位置，区别点主键索引不能重复，辅助索引可重复。
3. InnoDB自适应哈希索引 将热数据直接读到缓存中，数据结构用hash存储，将随机读改为顺序读，也不用走B+树索引查找。时间复杂度O(1)
  - 适用于唯一索引，还是普通索引也使用，冲突用哈希冲突来解决？

# MySQL 其他知识点
1. InnoDB 支持4中事务。
  - RN Read unCommited  读未提交，产生脏读
  - RC Read Commited    读已提交，产生不可重复读、幻读
  - RR Repeate Read     可产生幻读
  - SERIALIZABLE        稳

[how-to-produce-phantom-reads](https://stackoverflow.com/questions/5444915/how-to-produce-phantom-reads)

2. MVCC Mutil Version Concurrency Control  提高读写并发

3. 插入缓冲，Insert Buffer。对于辅助索引的插入或更新操作，不是每一次直接插入到索引页中，而是先判断插入的辅助索引是否在缓冲池中，若在，直接插入；若不在，则先放入到一个insert buffer中。

4. 异步IO，IO merge，系统判断出相邻的，就进行IO Merge 减少IO操作。

5. double write InnoDB存储引擎的是数据页的可靠性

