package com.wkk.learn.java.spring.boot.redis.controller;

import com.wkk.learn.java.spring.boot.redis.service.InventoryReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 模拟减库存操作
 * @Author Wangkunkun
 * @Date 2021/1/6 21:04
 */
@RequestMapping("/inventory")
@RestController
public class InventoryReductionController {

    @Autowired
    private InventoryReductionService inventoryReductionService;

    @GetMapping
    public boolean inventoryReduction() {
        try {
            return inventoryReductionService.inventoryReductionByRedisLock(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
