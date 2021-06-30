package com.rpc.rpc.myRPCVersion5.server;

import com.rpc.rpc.myRPCVersion5.register.ServiceRegister;
import com.rpc.rpc.myRPCVersion5.register.zkServiceRegister;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    private ServiceRegister serviceRegister;
    private String host;
    private Integer port;
    private Map<String, Object> interfaceProvider;

    public ServiceProvider(String host, Integer port) {
        this.host = host;
        this.port = port;
        this.serviceRegister = new zkServiceRegister();
        this.interfaceProvider = new HashMap<>();
    }

    public ServiceProvider(Map<String, Object> interfaceProvider) {
        this.interfaceProvider = interfaceProvider;
    }

    public void provideServiceInterface(Object service){
        // 获取service所对应的类实现的所有接口
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for(Class clazz : interfaces){
            // clazz.getName() 获取的是全类名 com.rpc.rpc.myRPCVersion2.service.XXX
            interfaceProvider.put(clazz.getName(),service);
            System.out.println(clazz.getName());
            serviceRegister.register(clazz.getName(), new InetSocketAddress(host, port));
        }

    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
