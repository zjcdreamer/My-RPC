package com.rpc.rpc.myRPCVersion5.server;

import com.rpc.rpc.myRPCVersion5.service.BlogService;
import com.rpc.rpc.myRPCVersion5.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class SimpleTestServer {
    public static void main(String[] args) {
        // 创建Map对象
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        Map<String, Object> serverProvide = new HashMap<>();
//        serverProvide.put("UserService", userService);
//        serverProvide.put("BlogService", blogService);
//        RPCServer rpcServer = new SimpleRPCServer(serverProvide);
//        rpcServer.start(8899);
//        RPCServer rpcServer = new ThreadPoolRPCServer(serverProvide);
//        rpcServer.start(8899);
        ServiceProvider serviceProvider = new ServiceProvider(serverProvide);
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);
        RPCServer rpcServer = new SimpleRPCServer(serverProvide);
        rpcServer.start(8899);
    }
}
