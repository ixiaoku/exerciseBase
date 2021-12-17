package com.dcr.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @descriptions: 实现 Callable 接口
 * @author: dcr
 * @date: 2021/7/19 16:02
 */
public class CallableThread implements Callable<Integer> {

    public static void main(String[] args) throws Exception {
        CallableThread callableThread = new CallableThread();
        FutureTask<Integer> futureTask = new FutureTask<>(callableThread);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }

    @Override
    public Integer call() {
        return 1234;
    }
}
