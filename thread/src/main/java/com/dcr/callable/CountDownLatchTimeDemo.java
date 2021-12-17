package com.dcr.callable;


import com.dcr.enums.TimeEnum;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @descriptions: 线程按顺序执行
 * @author: dcr
 * @date: 2021/12/15 16:06
 */
public class CountDownLatchTimeDemo {

    private final static int cnt = 6;

    public static void main(String[] args) throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(cnt);
        for(int i = 1; i <= cnt; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + "：朝开始了");
                countDownLatch.countDown();
            }, TimeEnum.getInstance(i).getValue()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "：中国的历朝历代");
    }

}
