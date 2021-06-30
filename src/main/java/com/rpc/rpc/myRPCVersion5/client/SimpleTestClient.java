package com.rpc.rpc.myRPCVersion5.client;

import com.rpc.rpc.myRPCVersion5.entity.Blog;
import com.rpc.rpc.myRPCVersion5.entity.User;
import com.rpc.rpc.myRPCVersion5.service.BlogService;
import com.rpc.rpc.myRPCVersion5.service.UserService;

import java.util.Random;
import java.util.UUID;

public class SimpleTestClient {
    public static void main(String[] args) {
        // 指定IP和端口号
        SimpleRPClient simpleRPClient = new SimpleRPClient();
        ClientProxy clientProxy = new ClientProxy(simpleRPClient);
        UserService proxy = clientProxy.getProxy(UserService.class);
        // 通过代理调用目标对象方法
        User user = proxy.getUserById(1000);
        System.out.println("获取到用户：" + user);
        // 调用UserService的服务
        User user1 = User.builder().name(UUID.randomUUID().toString())
                .gender(new Random().nextBoolean())
                .Id(new Random().nextInt()).build();
        Integer integer = proxy.insertUser(user1);
        System.out.println("成功插入" + integer + "条记录");

        // 调用BlogService的服务
        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(10);
        System.out.println("客户端查询到id为：" + blogById + " 的博客");
    }
}
