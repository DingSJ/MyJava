package com.china.designPattern.proxy;

/**
 *     <dependency>
 *       <groupId>cglib</groupId>
 *       <artifactId>cglib</artifactId>
 *       <version>3.2.5</version>
 *     </dependency>
 *
 *  在Spring的AOP编程中：
      如果加入容器的目标对象有实现接口，就使用JDK代理
      如果目标对象没有实现接口，就使用Cglib代理。


 代理的对象不能为final的，否则会报错。
 目标对象的方法如果为final/static修饰的，那么就不会被拦截，即不会执行目标对象额外的方法。
 *
 * */
public class TestProxyFactory {
    public static void main (String[] args) {
        //目标对象
        UserDao userDao = new UserDao();
        //生成代理对象
        UserDao userDaoProxy = (UserDao) new ProxyFactory(userDao).getProxyInstance();
        //调用对象方法
        userDaoProxy.save();
    }
}