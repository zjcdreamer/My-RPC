package com.rpc.rpc.myRPCVersion4.server;

import com.rpc.rpc.myRPCVersion4.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion4.entity.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class WorkThread implements Runnable{
    private Socket socket;
    private Map<String, Object> serverProvide;

    public WorkThread() {
    }

    public WorkThread(Socket socket, Map<String, Object> serverProvide) {
        this.socket = socket;
        this.serverProvide = serverProvide;
    }

    @Override
    public void run() {
        try {
            // 获取请求数据并解析成RPCRequest类型，返回调用getResponse来获取响应数据
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // 读
            RPCRequest request = (RPCRequest) ois.readObject();
            RPCResponse response = getResponse(request);
            // 响应
            oos.writeObject(response);
            oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private RPCResponse getResponse(RPCRequest request) {
        // 通过反射获取该方法使用的服务
        Object o = serverProvide.get(request.getInterfaceName());
        try {
            // 获取服务的方法
            Method method = o.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invoke = method.invoke(o, request.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("方法执行出错");
            e.printStackTrace();
            return RPCResponse.fail();
        }
    }
}
