package com.wkk.lean.java.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description 内存溢出测试
 * @Author Wangkunkun
 * @Date 2020/10/26 15:33
 */
public class MemoryOverflowTest {

    static class Key {
        Integer id;

        public Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

    }
    static Map map = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            if(!map.containsKey(new Key(i))) {
                map.put(new Key(i), "number " + i);
            }
        }
        System.out.println("map size : "+ map.size());

        for (int i = 0; i < 9000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
