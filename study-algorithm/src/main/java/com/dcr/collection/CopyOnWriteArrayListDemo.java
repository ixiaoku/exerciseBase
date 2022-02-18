package com.dcr.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/30 10:34
 */
public class CopyOnWriteArrayListDemo {

    public class ReadTask implements Runnable{

        private List<String> list;

        public ReadTask(List<String> list){
            this.list = list;
        }

        @Override
        public void run() {
            for(String str: list){
                System.out.println(str);
            }
        }
    }

    public class WriteTask implements Runnable{

        private List<String> list;
        private int index;

        public WriteTask(List<String> list, int index){
            this.list = list;
            this.index = index;
        }

        @Override
        public void run() {
            list.remove(index);
            list.add(index, "write_" + index);
        }
    }

    public void run() {
        final int NUM = 10;
        //因为我们在读取的时候，对列表进行了修改 报ConcurrentModificationException异常
        //List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < NUM; i++) {
            list.add("main_" + i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(NUM);
        for (int i = 0; i < NUM; i++) {
            executorService.execute(new ReadTask(list));
            executorService.execute(new WriteTask(list, i));
        }
        executorService.shutdown();
    }

    public static void main(String[] args){

        new CopyOnWriteArrayListDemo().run();
    }

}
