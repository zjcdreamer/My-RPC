package com.rpc.rpc.myRPCVersion6.service;

import com.rpc.rpc.myRPCVersion6.entity.User;

public interface UserService {

    User getUserById(Integer Id);

    Integer insertUser(User user);
}
