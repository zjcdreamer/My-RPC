package com.rpc.rpc.myRPCVersion6.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rpc.rpc.myRPCVersion6.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion6.entity.RPCResponse;

public class JsonSerializer implements Serializer {
    @Override
    public byte[] serialize(Object obj) {
        byte[] bytes = JSONObject.toJSONBytes(obj);
        return bytes;
    }
    Object obj = null;
    @Override
    public Object deSerialize(byte[] bytes, int messageType) {
        switch (messageType) {
            case 0:
                RPCRequest request = JSON.parseObject(bytes, RPCRequest.class);
                if (request.getParams().length == 0) return request;

                Object[] params = new Object[request.getParams().length];
                for (int i = 0; i < request.getParams().length; i++) {
                    Class<?> paramsTypes = request.getParamsTypes()[i];
                    if (!paramsTypes.isAssignableFrom(request.getParams()[i].getClass())) {
                        params[i] = JSONObject.toJavaObject((JSONObject) request.getParams()[i],request.getParamsTypes()[i]);
                    } else {
                        params[i] = request.getParams()[i];
                    }
                }
                request.setParams(params);
                obj = request;
                break;
            case 1:
                RPCResponse response = JSON.parseObject(bytes, RPCResponse.class);
                Class<?> dataType = response.getDataType();
                if (!dataType.isAssignableFrom(response.getData().getClass())) {
                    response.setData(JSONObject.toJavaObject((JSONObject) response.getData(),response.getDataType()));
                }
                obj = response;
                break;
            default:
                System.out.println("暂时不支持此种消息");
                throw new RuntimeException();
        }
//        System.out.println("JsonSerializer类的deSerialize方法。" + obj);
        return obj;
    }

    @Override
    public int getType() {
        return 1;
    }
}
