package com.rpc.rpc.myRPCVersion4.server;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    private Map<String, Object> interfaceProvider;

    public ServiceProvider() {
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
        }

    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
