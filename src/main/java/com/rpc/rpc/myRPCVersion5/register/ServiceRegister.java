package com.rpc.rpc.myRPCVersion5.register;

import java.net.InetSocketAddress;

public interface ServiceRegister {
    void register(String interfaceName, InetSocketAddress address);

    InetSocketAddress serviceDiscovery(String interfaceName);
}
