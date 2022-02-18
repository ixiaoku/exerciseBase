package com.dcr.jdk;

/**
 * @descriptions: 静态方法可以被继承 但不能被重写 方法是通过类名.方法来调用的
 * @author: dcr
 * @date: 2021/12/30 14:59
 */
public class A {

    public static class B {
        public static void a(){
            System.out.println("B");
        }
    }

    public static class C extends B{
        public static void a(){
            System.out.println("C");
        }
    }

    public static void main(String[] args) {
        C.a();
    }

}
