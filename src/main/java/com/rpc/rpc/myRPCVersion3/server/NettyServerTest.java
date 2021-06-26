package com.rpc.rpc.myRPCVersion3.server;

import com.rpc.rpc.myRPCVersion3.service.BlogService;
import com.rpc.rpc.myRPCVersion3.service.UserService;

public class NettyServerTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer RPCServer = new NettyRPCServer(serviceProvider);
        RPCServer.start(8899);
    }
}
