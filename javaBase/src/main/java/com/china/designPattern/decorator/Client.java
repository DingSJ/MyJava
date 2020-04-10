package com.china.designPattern.decorator;

/**
 * 关于装饰器模式的使用，在我看来主要有一下几点需要注意的
 *
 * 抽象装饰器和具体被装饰的对象实现同一个接口
 * 抽象装饰器里面要持有接口对象，以便请求传递
 * 具体装饰器覆盖抽象装饰器方法并用super进行调用，传递请求
 *
 * 1. 适用场景
 * 扩展一个类的功能。
 * 动态添加功能，动态撤销。
 *
 * 2. 优点
 * 装饰类和被装饰类都只关心自身的核心业务，实现了解耦。
 * 方便动态的扩展功能，且提供了比继承更多的灵活性。
 *
 * 3. 缺点
 * 如果功能扩展过多，势必产生大量的类。
 * 多层装饰比较复杂。
 *
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("=========我是土豪都给我加上===========");
        IPancake pancake = new Pancake();
        IPancake pancakeWithEgg = new EggDecorator(pancake);
        IPancake pancakeWithEggAndHam = new HamDecorator(pancakeWithEgg);
        IPancake panckeWithEggAndHamAndLettuce = new LettuceDecorator(pancakeWithEggAndHam);
        panckeWithEggAndHamAndLettuce.cook();

        System.out.println("==========我是程序猿，加两个鸡蛋补补==============");
        IPancake pancake2 = new Pancake();
        IPancake pancakeWithEgg2 = new EggDecorator(pancake2);
        IPancake pancakeWithTwoEgg = new EggDecorator(pancakeWithEgg2);
        pancakeWithTwoEgg.cook();
    }
}