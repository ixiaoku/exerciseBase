package com.dcr.callable;


import java.util.concurrent.CountDownLatch;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/15 16:06
 */
public class CountLanchDemo {

    private final static int cnt = 6;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(cnt);

        for(int i = 0; i < cnt; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + "：同学离开教室了");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "：班长最后离开锁门");

    }

}
