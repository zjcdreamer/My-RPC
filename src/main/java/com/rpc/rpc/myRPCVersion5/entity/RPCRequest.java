package com.rpc.rpc.myRPCVersion5.entity;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RPCRequest implements Serializable {
    // 接口名称
    private String interfaceName;
    // 方法名称
    private String methodName;
    // 参数
    private Object[] params;
    // 参数类型
    private Class<?>[] paramsTypes;
}
