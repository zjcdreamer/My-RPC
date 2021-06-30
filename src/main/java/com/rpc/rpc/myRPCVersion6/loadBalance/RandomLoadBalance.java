package com.rpc.rpc.myRPCVersion6.loadBalance;

import java.util.List;
import java.util.Random;

public class RandomLoadBalance implements LoadBalance {
    @Override
    public String Balance(List<String> addressList) {
        Random random = new Random();
        int i = random.nextInt(addressList.size());
        System.out.println("选择了第 " + i + " 台服务区");
        return addressList.get(i);
    }
}
