package com.wkk.learn.java.spring.boot.redis.service;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2021/1/6 16:06
 */
public interface InventoryReductionService {
    boolean inventoryReductionByRedisLock(int num) throws Exception;
}
