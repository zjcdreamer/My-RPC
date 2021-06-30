package com.rpc.rpc.myRPCVersion6.register;

import java.net.InetSocketAddress;

public interface ServiceRegister {
    void register(String interfaceName, InetSocketAddress address);

    InetSocketAddress serviceDiscovery(String interfaceName);
}
