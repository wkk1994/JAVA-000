package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.annotation.RpcfxService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import org.springframework.stereotype.Component;

/**
 * @Description 查询用户信息service
 * @Author Wangkunkun
 * @Date 2020/12/16 22:31
 */
@Component
public class QueryUserService {

    @RpcfxService(url = "http://localhost:8080/")
    private UserService userService;

    @RpcfxService(url = "http://localhost:8181/")
    private UserService userService1;

    public User findById(int i) {
        return userService.findById(i);
    }

    public User findById1(int i) {
        return userService1.findById(i);
    }
}
