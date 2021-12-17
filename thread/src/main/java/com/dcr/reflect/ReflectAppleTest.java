package com.dcr.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/16 17:30
 */
public class ReflectAppleTest {

    private Integer price;

    private String name;

    public ReflectAppleTest() {
    }

    public ReflectAppleTest(Integer price, String name) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        //通过反射 获取Class类对象
        Class appleClass1 = Class.forName("com.dcr.reflect.ReflectAppleTest");
        //使用.class方法 获取Class类对象
        Class appleClass2 = ReflectAppleTest.class;
        //使用类对象的 getClass()方法 获取Class类对象
        ReflectAppleTest reflectAppleTest = new ReflectAppleTest();
        Class appleClass3 = reflectAppleTest.getClass();

        //通过反射创建类对象
        //第一种：通过 Class 对象的 newInstance() 方法。
        ReflectAppleTest appleTest1 = (ReflectAppleTest) appleClass2.newInstance();
        appleTest1.setPrice(15);
        System.out.println("appleTest1价格：" + appleTest1.getPrice());
        //第二种：通过 Constructor 对象的 newInstance() 方法
        Constructor constructor = appleClass2.getConstructor();
        ReflectAppleTest appleTest2 = (ReflectAppleTest) constructor.newInstance();
        appleTest2.setPrice(15);
        System.out.println("appleTest2价格：" + appleTest2.getPrice());

        //通过反射获取类属性、方法、构造器
        Field[] fields = appleClass1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("通过反射获取类的属性:" + field.getName());
        }
        Method[] methods = appleClass1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("通过反射获取类的方法:" + method.getName());
        }
        Constructor<?>[] constructors = appleClass1.getDeclaredConstructors();
        for (Constructor constructor1 : constructors) {
            System.out.println("通过反射获取类的构造器:" + constructor1.getName());
        }

        //当某个反射调用的调用次数在 15 之下时，采用本地实现；当达到 15 时，便开始动态生成字节码，并将委派实现的委派对象切换至动态实现，这个过程我们称之为 Inflation。
        Method method = appleClass1.getMethod("target", int.class);
        for (int i = 0; i < 20; i++) {
            method.invoke(null, i);
        }
    }
}
