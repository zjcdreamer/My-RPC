package com.rpc.rpc.myRPCVersion1.client;

import com.rpc.rpc.myRPCVersion1.service.UserService;
import com.rpc.rpc.myRPCVersion1.entity.User;

import java.util.Random;
import java.util.UUID;

public class RPClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("localhost", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);
        // 通过代理调用目标对象方法
        User user = proxy.getUserById(1000);
        System.out.println("获取到用户：" + user);
        //
        User user1 = User.builder().name(UUID.randomUUID().toString())
                .gender(new Random().nextBoolean())
                .Id(new Random().nextInt()).build();
        Integer integer = proxy.insertUser(user1);
        System.out.println("成功插入" + integer + "条记录");
    }
}
