package com.rpc.rpc.myRPCVersion2.service;

import com.rpc.rpc.myRPCVersion2.entity.User;

public interface UserService {

    User getUserById(Integer Id);

    Integer insertUser(User user);
}
