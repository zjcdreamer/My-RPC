package com.rpc.rpc.myRPCVersion0.client;

import com.rpc.rpc.myRPCVersion0.entity.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPClient {
    public static void main(String[] args) {
        {
            try {
                // 创建Socket连接
                Socket socket = new Socket("localhost", 8899);
                System.out.println("客户端启动成功");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                // 传给服务器查询的Id
                objectOutputStream.writeInt(new Random().nextInt());
                objectOutputStream.flush();

                // 服务器端根据Id查询，客户端拿到结果
                User user = (User) objectInputStream.readObject();
                System.out.println("服务器端查询的用户：" + user);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("客户端启动失败");
            }
        }
    }


}
