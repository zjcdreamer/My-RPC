package com.rpc.rpc.myRPCVersion5.server;

import com.rpc.rpc.myRPCVersion5.entity.User;
import com.rpc.rpc.myRPCVersion5.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Integer Id) {
        System.out.println("服务器端查询了 Id 为"  + Id + " 的用户");

        Random random = new Random();

        User user = User.builder().name(UUID.randomUUID().toString())
                .Id(Id)
                .gender(random.nextBoolean()).build();
        return user;
    }

    @Override
    public Integer insertUser(User user) {
        System.out.println("插入用户信息成功");
        return 1;
    }
}
