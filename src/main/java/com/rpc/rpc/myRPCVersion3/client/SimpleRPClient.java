package com.rpc.rpc.myRPCVersion3.client;

import com.rpc.rpc.myRPCVersion3.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion3.entity.RPCResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@NoArgsConstructor
@AllArgsConstructor
public class SimpleRPClient implements RPClient{
    private String host;
    private Integer port;

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
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
            System.out.println("客户端启动失败");
            return null;
        }
    }
}
