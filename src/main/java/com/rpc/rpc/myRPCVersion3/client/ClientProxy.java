package com.rpc.rpc.myRPCVersion3.client;

import com.rpc.rpc.myRPCVersion3.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion3.entity.RPCResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@NoArgsConstructor
@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    public RPClient client;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes()).build();
        RPCResponse response = client.sendRequest(request);
        return response.getData();
    }

    <T> T getProxy(Class<T> tClass) {
        Object o = Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, this);
        return (T)o;
    }
}
