package com.rpc.rpc.myRPCVersion5.server;

import com.rpc.rpc.myRPCVersion5.service.BlogService;
import com.rpc.rpc.myRPCVersion5.service.UserService;

public class NettyServerTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 8899);
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer RPCServer = new NettyRPCServer(serviceProvider);
        RPCServer.start(8899);
    }
}
