package com.rpc.rpc.myRPCVersion5.client;

import com.rpc.rpc.myRPCVersion5.entity.Blog;
import com.rpc.rpc.myRPCVersion5.entity.User;
import com.rpc.rpc.myRPCVersion5.service.BlogService;
import com.rpc.rpc.myRPCVersion5.service.UserService;

public class NettyClientTest {
    public static void main(String[] args) {
        NettyRPClient nettyRPClient = new NettyRPClient();
        ClientProxy clientProxy = new ClientProxy(nettyRPClient);

        UserService userService = clientProxy.getProxy(UserService.class);
        User userById = userService.getUserById(1000);
        System.out.println("从服务端得到的user为：" + userById);

        User user = User.builder().name("张三").Id(100).gender(true).build();
        Integer integer = userService.insertUser(user);
        System.out.println("向服务端插入数据："+integer);

        BlogService blogService = clientProxy.getProxy(BlogService.class);

        Blog blogById = blogService.getBlogById(10000);
        System.out.println("从服务端得到的blog为：" + blogById);
    }
}
