package com.dcr.callable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/27 13:55
 */
public class TestThread {

    public static void main(String[] args) {

        get();

    }

    public static void get() {

        AtomicInteger num = new AtomicInteger(0);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("结果0： " + 1 / num.get());
        }).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());

            System.out.println("结果1： " + num.get());
        }).start();

        System.out.println("结果2： " + num.get());

    }

}
