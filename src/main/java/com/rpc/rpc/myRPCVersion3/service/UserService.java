package com.rpc.rpc.myRPCVersion3.service;

import com.rpc.rpc.myRPCVersion3.entity.User;

public interface UserService {

    User getUserById(Integer Id);

    Integer insertUser(User user);
}
