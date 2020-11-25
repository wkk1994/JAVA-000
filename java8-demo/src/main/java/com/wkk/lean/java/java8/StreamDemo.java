package com.wkk.lean.java.java8;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Description java stream操作demo
 * @Author Wangkunkun
 * @Date 2020/11/22 21:48
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<String>  list = new ArrayList<>();
        list.add("6");
        list.add("8");
        list.add("3");
        list.add("6");
        list.add("9");
        list.add("18");
        list.add("10");
        list.add("1");
        Predicate<String> predicate1 = new Predicate<String>() {
            @Override
            public boolean test(String t) {
                return false;
            }
        };
        System.out.println(Arrays.toString(list.stream().filter((t) -> t.compareTo("3") > 0).toArray()));
        System.out.println(Arrays.toString(list.stream().distinct().toArray()));
        System.out.println(Arrays.toString(list.stream().limit(5).toArray()));
        System.out.println(Arrays.toString(list.stream().skip(5).toArray()));
        System.out.println(Arrays.toString(list.stream().map(t -> t + "--").toArray()));
        System.out.println(Arrays.toString(list.stream().mapToInt(Integer::valueOf).toArray()));
        Predicate<String> predicate = t -> {
            if (t.equals("10")) {
                return true;
            }else {
                return false;
            }
        };
        if (list.stream().allMatch(predicate)) {
            System.out.println("allMatch true");
        }
        if (list.stream().anyMatch(predicate)) {
            System.out.println("anyMatch true");
        }

        if (list.stream().noneMatch(predicate)) {
            System.out.println("anyMatch true");
        }
        System.out.println("findFirst " + list.stream().findFirst().get());
        System.out.println("findAny " + list.stream().findAny().get());
        System.out.println("count " + list.stream().count());
        Comparator<String> comparator = String::compareTo;
        System.out.println("max " + list.stream().max(comparator).get());
        System.out.println("min " + list.stream().min(comparator).get());
        BinaryOperator<String> tBinaryOperator = (s, s2) -> s + s2;
        System.out.println("reduce : " + list.stream().reduce(tBinaryOperator).get());
        System.out.println("reduce : " + list.stream().reduce("0", tBinaryOperator));

        // (a, b) -> 表示当key重复的时候怎么处理，不添加这个会在有重复key的时候出现错误。
        System.out.println("collect.toMap : " + list.stream().collect(Collectors.toMap(t -> t, t -> t+"123", (a, b) -> a, HashMap::new)));

        //System.out.println("collect.toMap : " + list.stream().collect(Collectors.toMap(t -> t, t -> t+"123")));
        System.out.println("collect.counting : " + list.stream().collect(Collectors.counting()));
        System.out.println("collect.summarizingInt : " + list.stream().collect(Collectors.summarizingInt(Integer::valueOf)));
        //System.out.println("collect : " + list.stream().collect(Collectors.toCollection(t -> t));

    }
}
