package com.rpc.rpc.myRPCVersion1.service;

import com.rpc.rpc.myRPCVersion1.entity.User;

public interface UserService {

    User getUserById(Integer Id);

    Integer insertUser(User user);
}
