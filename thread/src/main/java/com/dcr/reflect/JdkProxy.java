package com.dcr.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/28 18:31
 */
public class JdkProxy implements InvocationHandler {

    /**
     * 需要代理的目标对象
     */
    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，监听开始！");
        Object result = method.invoke(target, args);
        System.out.println("JDK动态代理，监听结束！");
        return result;
    }

    /**
     * 定义获取代理对象方法
     * @param targetObject
     * @return
     */
    private Object getJDKProxy(Object targetObject) {
        //为目标对象target赋值
        this.target = targetObject;
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        System.out.println(this.getClass());
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        //实例化JDKProxy对象
        JdkProxy jdkProxy = new JdkProxy();
        //获取代理对象
        UserManager user = (UserManager) jdkProxy.getJDKProxy(new UserManagerImpl());
        //执行新增方法
        user.addUser("admin", "123123");
    }

}
