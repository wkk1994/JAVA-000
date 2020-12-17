package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.annotation.RpcfxService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/12/17 13:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QueryUserService queryUserService;

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Integer id) {
        return queryUserService.findById(id);
    }
}
