package com.china.designPattern.proxy;

/**
 * 真实的角色
 */
public class RealSubject implements ISubject {
 
	@Override
	public void request() {
		System.out.println("XXXXXXXXXXXXXXXXX");
	}
 
}