package com.dcr.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2020/10/15 16:50
 */
public class MyBloomFilter {

    /**
     * 数组长度
     */
    private int arraySize;

    /**
     * 数组
     */
    private int[] array;

    public MyBloomFilter(int arraySize) {
        this.arraySize = arraySize;
        array = new int[arraySize];
    }

    /**
     * 写入数据
     *
     * @param key
     */
    public void add(String key) {
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);
        int indexFirst = first % arraySize;
        int indexSecond = second % arraySize;
        int indexThird = third % arraySize;
        array[indexFirst] = 1;
        array[indexSecond] = 1;
        array[indexThird] = 1;
    }

    /**
     * 判断数据是否存在
     *
     * @param key
     * @return
     */
    public boolean check(String key) {
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);

        int firstIndex = array[first % arraySize];
        if (firstIndex == 0) {
            return false;
        }

        int secondIndex = array[second % arraySize];
        if (secondIndex == 0) {
            return false;
        }

        int thirdIndex = array[third % arraySize];
        if (thirdIndex == 0) {
            return false;
        }

        return true;

    }


    /**
     * hash 算法1
     *
     * @param key
     * @return
     */
    private int hashcode_1(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }

    /**
     * hash 算法2
     *
     * @param data
     * @return
     */
    private int hashcode_2(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }

    /**
     * hash 算法3
     *
     * @param key
     * @return
     */
    private int hashcode_3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash);
    }

    public static void main(String[] args) {
        bloomTest();
        //guavaTest();
    }

    public static void bloomTest(){
        long star = System.currentTimeMillis();
        MyBloomFilter bloomFilters = new MyBloomFilter(10000000);
        for (int i = 0; i < 1000000; i++) {
            bloomFilters.add(String.valueOf(i));
        }
        System.out.println(bloomFilters.check(1 + ""));
        System.out.println(bloomFilters.check(2 + ""));
        System.out.println(bloomFilters.check(3 + ""));
        System.out.println(bloomFilters.check(999999 + ""));
        System.out.println(bloomFilters.check(400230340 + ""));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }

    public static void guavaTest() {
        long star = System.currentTimeMillis();
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                10000000,
                0.01);

        for (int i = 0; i < 10000000; i++) {
            filter.put(i);
        }
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        System.out.println(filter.mightContain(3));
        System.out.println(filter.mightContain(10000000));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }
}
