package com.rpc.rpc.myRPCVersion2.server;

import java.util.Map;

public class ServiceProvider {
    private Map<String, Object> interfaceProvider;

    public ServiceProvider(Map<String, Object> interfaceProvider) {
        this.interfaceProvider = interfaceProvider;
    }

    public void provideServiceInterface(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for(Class clazz : interfaces){
            interfaceProvider.put(clazz.getName(),service);
        }

    }
}
