package com.rpc.rpc.myRPCVersion2.client;

import com.rpc.rpc.myRPCVersion2.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion2.entity.RPCResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy implements InvocationHandler {
    private String host;
    private Integer port;

    public ClientProxy() {
    }

    public ClientProxy(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes()).build();
        RPCResponse response = IOClient.sentRequest(host, port, request);
        return response.getData();
    }

    <T> T getProxy(Class<T> tClass) {
        Object o = Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, this);
        return (T)o;
    }
}
