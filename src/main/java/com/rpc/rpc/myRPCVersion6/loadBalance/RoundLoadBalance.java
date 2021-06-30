package com.rpc.rpc.myRPCVersion6.loadBalance;

import java.util.List;

public class RoundLoadBalance implements LoadBalance {
    private int choose = -1;

    @Override
    public String Balance(List<String> addressList) {
        choose++;
        choose = choose % addressList.size();
        System.out.println("选择了第 " + choose + " 台服务器");
        return addressList.get(choose);
    }
}
