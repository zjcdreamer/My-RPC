package com.rpc.rpc.myRPCVersion6.codec;

import com.rpc.rpc.myRPCVersion6.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion6.entity.RPCResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MyEncoder extends MessageToByteEncoder {
    private Serializer serializer;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
//        System.out.println("MyEncoder中msg的类型： " + msg.getClass());
        // 消息类型
        if (msg instanceof RPCRequest) {
            byteBuf.writeShort(MessageType.REQUEST.getCode());
        } else if (msg instanceof RPCResponse) {
            byteBuf.writeShort(MessageType.RESPONSE.getCode());
        }
        // 序列化类型
        byteBuf.writeShort(serializer.getType());
        // 根据code得到序列化器进行序列化
        Serializer serializer = Serializer.getSerializerByCode(this.serializer.getType());
        byte[] bytes = serializer.serialize(msg);
        // 序列化后的数据长度
        byteBuf.writeShort(bytes.length);
        // 序列化数据
        byteBuf.writeBytes(bytes);
    }
}
