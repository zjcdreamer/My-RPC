package com.rpc.rpc.myRPCVersion4.codec;

import java.io.*;

public class ObjectSerializer implements Serializer {
    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            byte[] bytes = bos.toByteArray();
            oos.close();
            bos.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public Object deSerialize(byte[] bytes, int messagetType) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object object = ois.readObject();
            ois.close();
            bis.close();
            return object;
        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getType() {
        return 0;
    }
}
