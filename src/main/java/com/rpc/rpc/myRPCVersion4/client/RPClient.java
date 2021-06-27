package com.rpc.rpc.myRPCVersion4.client;

import com.rpc.rpc.myRPCVersion4.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion4.entity.RPCResponse;

public interface RPClient {
    RPCResponse sendRequest(RPCRequest request);
}
