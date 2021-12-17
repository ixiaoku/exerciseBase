package com.dcr.jvm;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/7 17:41
 */
public class JOLExample1 {

    public static class A{}

    static A a;

    public static void main(String[] args) {
        a = new A();
        //打印JVM的详细信息
        System.out.println(VM.current().details());
        //打印对应的对象头信息
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }

}
