package com.dcr.string;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2022/1/10 14:59
 */
public class StaticTest {

    public void run(){
        System.out.println("爸爸跑步锻炼身体");
    }

    public static void swim(){
        System.out.println("爸爸游泳锻炼身体");
    }

    public static void main(String[] args) {
        StaticTest staticTest = new A();
        staticTest.run();
        staticTest.swim();
    }
}
