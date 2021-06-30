package com.rpc.rpc.myRPCVersion6.server;

import com.rpc.rpc.myRPCVersion6.entity.Blog;
import com.rpc.rpc.myRPCVersion6.service.BlogService;

public class BlogServiceImpl implements BlogService {

    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id)
                .userId(22)
                .title("博客").build();
        System.out.println("客户端查询了序号为：" + id + " 的博客");
        return blog;
    }
}
