package com.china.designPattern.mediator;

/**
 * 具体同事类：这里的角色是 房东
 *
 */
public class Landlord extends Person {
 
	public Landlord(String name, Mediator mediator) {
		super(name,mediator);
	}
	
	@Override
	protected void sendMessage(String msg) {
		mediator.operation(this, msg);
	}
 
	@Override
	protected void getMessage(String msg) {
		System.out.println("房东["+ name +"]收到中介发来的消息：" + msg);
	}
 
}
