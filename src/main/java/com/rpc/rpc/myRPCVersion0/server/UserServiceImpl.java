package com.rpc.rpc.myRPCVersion0.server;

import com.rpc.rpc.myRPCVersion0.entity.User;
import com.rpc.rpc.myRPCVersion0.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Integer Id) {
        System.out.println("服务器端查询了Id为"  + Id + " 的用户");

        Random random = new Random();

        User user = User.builder().name(UUID.randomUUID().toString())
                .Id(Id)
                .gender(random.nextBoolean()).build();
        return user;
    }
}
