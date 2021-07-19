package com.skyl.disruptor.znetty.client;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.skyl.disruptor.znetty.common.disruptor.MessageConsumer;
import com.skyl.disruptor.znetty.common.disruptor.RingBufferWorkerPoolFactory;

public class Main {
    public static void main(String[] args) {
        MessageConsumer[] conusmers = new MessageConsumer[4];
        for(int i =0; i < conusmers.length; i++) {
            MessageConsumer messageConsumer = new MessageConsumerImpl4Client("code:clientId:" + i);
            conusmers[i] = messageConsumer;
        }
        RingBufferWorkerPoolFactory.getInstance().initAndStart(ProducerType.MULTI,
                1024*1024,
                //new YieldingWaitStrategy(),
                new BlockingWaitStrategy(),
                conusmers);

        //建立连接 并发送消息
        new NettyClient().sendData();
    }
}
