package com.rpc.rpc.myRPCVersion5.service;

import com.rpc.rpc.myRPCVersion5.entity.User;

public interface UserService {

    User getUserById(Integer Id);

    Integer insertUser(User user);
}
