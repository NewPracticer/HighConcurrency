#  高并发学习路线

#### 概况
高并发基础知识以及高并发框架学习路线 （总时长：65小时）
 
#### 学习路线
1.  线程：https://coding.imooc.com/class/362.html (时长：20小时)
2.  JUC相关：https://coding.imooc.com/class/409.html （时长：19小时）
3.  并发面试：https://coding.imooc.com/class/195.html (时长: 12小时)
4.  高并发框架：https://coding.imooc.com/class/275.html （时长：14小时）


#### 线程 （后续更新）


#### JUC （后续更新）


#### 高并发框架 Disrputor
##### 快速入门
1. 性能
    1. 能在一个线程里每秒处理600万订单
    2. 业务逻辑完成运行在内存中，使用事件源驱动方式
    3. 使用定长数组，预加载
    4. CAS代替锁
    5. Cache Padding
    6. Memeory Barrier
2. 使用
    1. 建立一个工厂类，用于创建Event类实例对象
    2. 建立一个监听事件类，用于处理数据
    3. 实例化Disruptor实例，配置参数
    4. 建立一个生产者类，向disruptor中投递数据
3. 核心原理
    1. Ringbuffer ，数组实现的缓存，首尾相接
    2. Sequence  
        1. 通过顺序递增的序号来编号，管理进行交换的数据
        2. 对数据的处理过程总是沿着序号递增处理
        3. 一个序号用户跟踪标识某个特定的事件的处理进度
        4. 类似atomiclog用于标识进度
        5. 实现生产者和消费者之间快速、正确地传递数据的并发算法
    3. WaintStratgy  决定消费者将如何等待生产者将event置入Disruptor
        1. BlockingStrategy
            1. 最低效策略，对CPU消耗最小
        2. SleepingWaitStrategy
            1. 对生产者线程影响最小，适合异步日志
        3. YiedingWatiStrategy
            1. 性能最好，适用于低延迟
    4. EventProcessor 主要事件循环，处理Disruptor中的Event
    5. EventHandler 由用户实现，消费者逻辑主要是这里
    6. WorkProcessor 同一个WorkPool中处理多个workProcessor不会消费同样的sequence
    7. 依靠WorkerPool实现多消费者
4. netty 与 disruptor整合
    1. 不在工作线程中编写代码逻辑，利用disruptor 接受和处理 来提高性能
5. 实际应用
    1. 在金融系统的撮合交易中可以使用disruptor作为 撮合交易的核心 
