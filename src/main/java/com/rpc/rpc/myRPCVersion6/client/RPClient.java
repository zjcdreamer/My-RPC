package com.rpc.rpc.myRPCVersion6.client;

import com.rpc.rpc.myRPCVersion6.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion6.entity.RPCResponse;

public interface RPClient {
    RPCResponse sendRequest(RPCRequest request);
}
