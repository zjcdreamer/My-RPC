package com.rpc.rpc.myRPCVersion4.codec;

public interface Serializer {
    // 序列化
    byte[] serialize(Object obj);

    //反序列化
    Object deSerialize(byte[] bytes, int messagetType);

    // 获取类型
    int getType();

    static Serializer getSerializerByCode(int code) {
        switch (code) {
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }
}
