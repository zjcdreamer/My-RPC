package com.rpc.rpc.myRPCVersion0.server;

import com.rpc.rpc.myRPCVersion0.entity.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务器端启动成功");
            while (true) {
                // 接收到客户端请求建立Socket连接
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                        // 读取Id，调用服务层进行查询操作
                        int Id = objectInputStream.readInt();
                        User user = userServiceImpl.getUserById(Id);

                        // 传送给客户端
                        objectOutputStream.writeObject(user);
                        objectOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器端启动失败");
        }
    }


}
