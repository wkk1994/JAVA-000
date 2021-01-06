package com.wkk.learn.java.spring.boot.redis.service;

/**
 * @Description redis lock
 * @Author Wangkunkun
 * @Date 2021/1/6 13:40
 */
public interface RedisLockService {

    void lock();

    /**
     * 上锁
     * 返回true表示上锁成功，返回false表示上锁失败
     * @return
     */
    boolean tryLock();

    /**
     * 释放锁
     * 返回true表示释放锁成功，返回false表示释放锁失败，或者不存在锁
     * @return
     */
    boolean unLock() throws Exception;
}
