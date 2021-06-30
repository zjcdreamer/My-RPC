package com.rpc.rpc.myRPCVersion5.server;

import com.rpc.rpc.myRPCVersion5.register.ServiceRegister;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class SimpleRPCServer implements RPCServer {
    private Map<String, Object> serverProvide;
    private ServiceRegister serviceRegister;

    public SimpleRPCServer(Map<String, Object> serverProvide) {
        this.serverProvide = serverProvide;
    }


    @Override
    public void start(int port) {
        try {
            // 在这里就只负责监听
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动成功");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serverProvide)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
