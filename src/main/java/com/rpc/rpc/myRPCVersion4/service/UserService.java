package com.rpc.rpc.myRPCVersion4.service;

import com.rpc.rpc.myRPCVersion4.entity.User;

public interface UserService {

    User getUserById(Integer Id);

    Integer insertUser(User user);
}
