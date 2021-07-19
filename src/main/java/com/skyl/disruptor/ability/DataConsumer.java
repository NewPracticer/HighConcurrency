package com.skyl.disruptor.ability;

import com.lmax.disruptor.EventHandler;

/**
 * 数据消费者
 */
public class DataConsumer implements EventHandler<Data> {

    private long startTime;
    private int i;

    public DataConsumer() {
        this.startTime = System.currentTimeMillis();
    }

    public void onEvent(Data data, long seq, boolean bool)
            throws Exception {
        i++;
        if (i == Constants.EVENT_NUM_OHM) {
            long endTime = System.currentTimeMillis();
            System.out.println("Disruptor costTime = " + (endTime - startTime) + "ms");
        }
    }

}
