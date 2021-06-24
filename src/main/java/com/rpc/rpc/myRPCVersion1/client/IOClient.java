package com.rpc.rpc.myRPCVersion1.client;

import com.rpc.rpc.myRPCVersion1.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion1.entity.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {

    public static RPCResponse sentRequest(String host, int port, RPCRequest request) {
        try {
            // 和服务端建立连接
            Socket socket = new Socket(host, port);
            System.out.println("客户端启动成功");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // 向服务端发送请求
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            // 接收信息
            RPCResponse response = (RPCResponse) objectInputStream.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
