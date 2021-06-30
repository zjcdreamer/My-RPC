package com.rpc.rpc.myRPCVersion6.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class MyDecoder extends ByteToMessageDecoder {
    private Serializer serializer;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//        System.out.println("*********MyDecoder中的decode方法*********");
        // 消息类型
        short messageType = byteBuf.readShort();
        if (messageType != MessageType.REQUEST.getCode() && messageType != MessageType.RESPONSE.getCode()) {
            System.out.println("暂时不支持该类型数据");
            return;
        }

        // 序列化器类型
        short serializerCode = byteBuf.readShort();
        Serializer serializerByCode = Serializer.getSerializerByCode(serializerCode);

        // 序列化数据的长度
        short length = byteBuf.readShort();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Object o = serializerByCode.deSerialize(bytes, messageType);
        list.add(o);
    }
}
