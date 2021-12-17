package com.dcr.callable;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/7/19 16:12
 */
public class RunnableThread implements Runnable{


    @Override
    public void run() {
        System.out.println(1001 );
    }

    public static void main(String[] args) {
        RunnableThread runnableThread = new RunnableThread();
        Thread thread = new Thread(runnableThread);
        thread.start();
    }
}
