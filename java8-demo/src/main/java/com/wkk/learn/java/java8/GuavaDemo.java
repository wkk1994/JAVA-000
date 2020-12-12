package com.wkk.learn.java.java8;

import com.google.common.collect.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description guava Demo
 * @Author Wangkunkun
 * @Date 2020/11/23 21:00
 */
public class GuavaDemo {

    public static void main(String[] args) {
        // 不可变集合
        unmondifyCollection();

        newCollection();

        testEventBus();
    }

    /**
     * guava不可变集合
     */
    private static void unmondifyCollection() {
        ImmutableSet<String> of = ImmutableSet.of("c", "b", "a", "b", "f");
        System.out.println(of);
        List<String> list = new ArrayList<>();
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("f");
        list.add("a");
        of = ImmutableSet.copyOf(list);
        System.out.println(of);
    }

    /**
     * 新集合类
     */
    private static void newCollection() {
        Multiset multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("a");
        multiset.add("c");
        multiset.add("b");
        System.out.println(multiset);

        HashMultimap<Object, Object> hashMultimap = HashMultimap.create();
        hashMultimap.put("a", "b");
        hashMultimap.put("a", "c");
        hashMultimap.put("a", "d");
        hashMultimap.put("b", "d");
        System.out.println(hashMultimap);
        //Tables tables = Tables.toTable()
        HashBiMap<Object, Object> hashBiMap = HashBiMap.create();
        hashBiMap.put("a", "b");
        hashBiMap.put("b", "a");
        hashBiMap.put("b", "c");
        System.out.println(hashBiMap);
    }

    private static void testEventBus() {
        eventBus.post(new EventInfo("hello event"));
    }
    private static EventBus eventBus = new EventBus();

    static {
        eventBus.register(new GuavaDemo());
    }

    static class EventInfo{
        String name;

        public EventInfo(String name) {
            this.name = name;
        }
    }

    @Subscribe
    public void handle(EventInfo eventInfo) {
        System.out.println(eventInfo.name);
    }

    @Subscribe
    public void handle1(EventInfo eventInfo) {
        System.out.println(eventInfo.name);
    }
}
