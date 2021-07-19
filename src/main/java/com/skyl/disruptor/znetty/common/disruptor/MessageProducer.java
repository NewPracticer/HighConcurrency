package com.skyl.disruptor.znetty.common.disruptor;

import com.lmax.disruptor.RingBuffer;

import com.skyl.disruptor.znetty.common.entity.TranslatorData;
import com.skyl.disruptor.znetty.common.entity.TranslatorDataWapper;
import io.netty.channel.ChannelHandlerContext;

public class MessageProducer {

	private String producerId;
	
	private RingBuffer<TranslatorDataWapper> ringBuffer;
	
	public MessageProducer(String producerId, RingBuffer<TranslatorDataWapper> ringBuffer) {
		this.producerId = producerId;
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(TranslatorData data, ChannelHandlerContext ctx) {
		long sequence = ringBuffer.next();
		try {
			TranslatorDataWapper wapper = ringBuffer.get(sequence);
			wapper.setData(data);
			wapper.setCtx(ctx);
		} finally {
			ringBuffer.publish(sequence);
		}
	}

}
