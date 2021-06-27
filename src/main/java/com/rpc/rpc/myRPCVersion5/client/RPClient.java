package com.rpc.rpc.myRPCVersion5.client;

import com.rpc.rpc.myRPCVersion5.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion5.entity.RPCResponse;

public interface RPClient {
    RPCResponse sendRequest(RPCRequest request);
}
