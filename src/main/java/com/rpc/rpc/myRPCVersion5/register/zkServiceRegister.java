package com.rpc.rpc.myRPCVersion5.register;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import java.net.InetSocketAddress;
import java.util.List;

public class zkServiceRegister implements ServiceRegister {
    private CuratorFramework client;
    private static final String ROOT_PATH = "MyRPC";

    public zkServiceRegister() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(40000).retryPolicy(policy).namespace(ROOT_PATH).build();
        this.client.start();
        System.out.println("zookeeper连接成功");
    }

    @Override
    public void register(String interfaceName, InetSocketAddress address) {
        try {
            if (client.checkExists().forPath("/" + interfaceName) == null) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.CONTAINER).forPath("/" + interfaceName);
            }
            String path = "/" + interfaceName + "/" + getServiceAddress(address);
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("此服务已存在");
        }
    }

    @Override
    public InetSocketAddress serviceDiscovery(String interfaceName) {
        InetSocketAddress address = null;
        try {
            List<String> strings = client.getChildren().forPath("/" + interfaceName);
            String result = strings.get(0);
            address = parseAddress(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    private String getServiceAddress(InetSocketAddress address) {
        return address.getHostName() + ":" + address.getPort();
    }

    private InetSocketAddress parseAddress(String result) {
        String[] split = result.split(":");
        return  new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }
}
