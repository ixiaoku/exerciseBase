package com.dcr.collection;

import java.util.*;


/**
 * @descriptions:
 * @author: dcr
 * @date: 2022/2/10 18:06
 */
public class TeseInterotar {

    public static void main(String[] args){
        //qq();
        //qa();
        qb();
    }

    /**
     * Arrays.asList调用add remove方法会报UnsupportedOperationException
     */
    public static void qb(){
        List<Integer> list = Arrays.asList(1,2,-4,5,7,3);
        /*list.add(7);
        list.add(1);*/
        list.set(3,99);
        Comparator<Integer> comparator = (Comparator<Integer>) Integer::compareTo;
        list.sort(comparator);
        list.forEach(System.out::println);
    }
    /**
     * 增强for循环修改集合导致的ConcurrentModificationException
     */
    public static void qa(){
        List<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(1);
        for(Integer i : list){
            if(i == 7) {
                list.add(i);
            }
            System.out.println(i);
        }
    }

    /**
     * 迭代器Iterator循环遍历 修改集合导致的ConcurrentModificationException
     */
    public static void qq(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==2) {
                list.remove(integer);
            }
        }
    }

}
