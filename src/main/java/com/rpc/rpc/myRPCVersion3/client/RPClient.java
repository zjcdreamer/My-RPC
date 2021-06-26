package com.rpc.rpc.myRPCVersion3.client;

import com.rpc.rpc.myRPCVersion3.entity.RPCRequest;
import com.rpc.rpc.myRPCVersion3.entity.RPCResponse;

public interface RPClient {
    RPCResponse sendRequest(RPCRequest request);
}
