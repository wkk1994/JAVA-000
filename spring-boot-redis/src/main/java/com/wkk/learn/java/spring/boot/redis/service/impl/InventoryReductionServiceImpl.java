package com.wkk.learn.java.spring.boot.redis.service.impl;

import com.wkk.learn.java.spring.boot.redis.service.InventoryReductionService;
import com.wkk.learn.java.spring.boot.redis.service.RedisLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Description 模拟减库存操作
 * @Author Wangkunkun
 * @Date 2021/1/6 16:06
 */
@Service
public class InventoryReductionServiceImpl implements InventoryReductionService {

    @Autowired
    private RedisLockService redisLockService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 方式一：使用redis的分布式锁，在减库存之前，获取锁；减库存之后释放锁，实现减库存的线程安全。
     * @param num
     * @return
     */
    @Override
    public boolean inventoryReductionByRedisLock(int num) throws Exception {
        redisLockService.lock();
        // 业务处理
        try {
            ValueOperations<Serializable, String> valueOperations = redisTemplate.opsForValue();
            String numberStr = valueOperations.get("number");
            Long number = Long.valueOf(numberStr);
            if(number > 0) {
                valueOperations.decrement("number", num);
                System.out.println("减库存成功：" + number);
                return true;
            }else {
                return false;
            }
        } finally {
            redisLockService.unLock();
        }
    }
}
