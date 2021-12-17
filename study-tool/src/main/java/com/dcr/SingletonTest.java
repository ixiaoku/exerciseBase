package com.dcr;

/**
 * @descriptions: 双重校验锁
 * @author: dcr
 * @date: 2021/12/15 15:58
 */
public class SingletonTest {

    private static volatile SingletonTest singletonTest;

    private SingletonTest(){

    }

    private SingletonTest getInstance(SingletonTest singletonTest){
        if(singletonTest == null){
            synchronized (SingletonTest.class) {
                if(singletonTest == null){
                    singletonTest = new SingletonTest();
                }
            }
        }
        return singletonTest;
    }

}
