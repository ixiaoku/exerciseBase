package com.dcr.callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/7/19 16:27
 */
public class ExecutorThread {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new RunnableThread());
        }
        executorService.shutdown();
    }

}
