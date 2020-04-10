package com.china.designPattern.proxy;

/**
 * 静态代理  -  客户端调用
 */
public class StaticMain {
	public static void main(String[] args) {
		Subject subject = new ProxySubject();
		subject.request();  //代理者代替真实者做事情
	}
}