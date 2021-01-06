package com.wkk.learn.java.spring.boot.redis.service.impl;

import com.sun.tools.javac.util.List;
import com.wkk.learn.java.spring.boot.redis.service.RedisLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description redis lock
 * @Author Wangkunkun
 * @Date 2021/1/6 13:41
 */
@Service
public class RedisLockServiceImpl implements RedisLockService {

    private static Unsafe unsafe = null;

    @Autowired
    private RedisTemplate redisTemplate;

    private String workId = "1";

    private String lockKey = "lock.test";

    private Node head;

    private Node tail;

    private volatile Thread exclusiveOwnerThread;

    private static final long headOffset;
    private static final long tailOffset;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            headOffset = unsafe.objectFieldOffset
                    (RedisLockServiceImpl.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (RedisLockServiceImpl.class.getDeclaredField("tail"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    /**
     * 上锁，如果有锁会被阻塞
     * 返回true表示上锁成功，返回false表示上锁失败
     * @return
     */
    @Override
    public void lock() {
        //尝试获取锁
        if(tryLock()) {
            return;
        }
        // 尝试获取锁失败，加入到队列中等待获取锁
        Node node = addWaiter(new Node(Thread.currentThread()));
        // 当前线程尝试获取锁
        acquireQueued(node);

    }

    private void acquireQueued(Node node) {
        // 如果当前线程是头部的下一个节点，可以尝试获取锁，获取失败就park。
        for (;;) {
            Node prev = node.prev;
            if (prev == head) {
                if(tryLock()) {
                    head = node;
                    node.thread = null;
                    node.prev = null;
                    prev.prev = null;
                    prev.next = null;
                    return;
                }
            }else {
                LockSupport.park(this);
            }
        }
    }

    /**
     * 尝试上锁，只尝试一次
     * 返回true表示上锁成功，返回false表示上锁失败
     * @return
     */
    @Override
    public boolean tryLock() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (valueOperations.setIfAbsent(lockKey, workId, Duration.ofSeconds(30))) {
            this.exclusiveOwnerThread = Thread.currentThread();
            return true;
        }
        return false;
    }

    /**
     * 释放锁
     * 返回true表示释放锁成功，返回false表示释放锁失败，或者不存在锁
     * @return
     */
    @Override
    public boolean unLock() throws Exception {
        Node h = this.head;
        tryRelease();
        // 不论释放锁是否成功，都unpack下一个节点，因为可能锁信息过期或者被其他节点的资源获取到锁，导致实际上已经获取到锁的节点不能是否锁。
        if(h != null && h.next != null) {
            LockSupport.unpark(h.next.thread);
        }
        return true;
    }

    private boolean tryRelease() throws Exception {
        if(this.exclusiveOwnerThread  != Thread.currentThread()) {
            throw new Exception("当前线程没有获得锁不能释放锁资源");
        }
        this.exclusiveOwnerThread = null;
        RedisScript<Long> redisScript = RedisScript.of("if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1])else return 0 end", Long.class);
        Long aLong = (Long) redisTemplate.execute(redisScript, List.of(lockKey), workId);
        if(aLong == 1) {
            return true;
        }
        return false;
    }

    private Node addWaiter(Node mode) {
        //将当前节点添加到尾部
        Node pred = tail;
        if (pred != null) {
            mode.prev = pred;
            if(compareAndSetTail(pred, mode)) {
                pred.next = mode;
                return mode;
            }
        }
        //如果添加失败使用自旋添加
        for (;;) {
            Node t = tail;
            if (t == null) {
                if (compareAndSetHead(null, new Node()))
                    tail = head;
            } else {
                mode.prev = t;
                if (compareAndSetTail(t, mode)) {
                    t.next = mode;
                    return mode;
                }
            }
        }
    }

    private boolean compareAndSetTail(Node pred, Node mode) {
        return unsafe.compareAndSwapObject(this, tailOffset, pred, mode);
    }


    private boolean compareAndSetHead(Node pred, Node mode) {
        return unsafe.compareAndSwapObject(this, headOffset, pred, mode);
    }

    static class Node {

        volatile Node prev;

        volatile Node next;

        volatile Thread thread;

        public Node() {
        }

        public Node(Thread thread) {
            this.thread = thread;
        }
    }
}
