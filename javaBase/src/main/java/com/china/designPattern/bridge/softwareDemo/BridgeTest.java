package com.china.designPattern.bridge.softwareDemo;

/**
 * 优缺点
 * 	优点：抽象和实现的分离，优秀的扩展能力，实现细节对客户透明。
 * 	缺点：由于聚合关系建立在抽象层，要求开发者针对抽象化进行设计与编程，这增加了系统的理解与设计难度。
 *
 * 生活中的桥接模式
 * 	手机有很多不同的app，有购物、社交、音乐、视频、地图等等，每一款app都是一种算法实现，app属于被调用者，
 * 	手机作为调用者，又分很多种，有iphone、华为、小米、锤子等，这种场景可以使用桥接模式，实现调用者和被调用者分离。
 * 	汽车有很多零件，有轮胎、发动机、座椅、方向盘，汽车又分很多品牌，有保时捷、特斯拉、奔驰、宝马等，这种场景也同样适用于桥接模式。
 *
 * 我的启发
 * 	策略模式是对算法的抽象，调用者可以根据不同的情况，自主选择不同的算法实现。桥接模式是策略模式的升级版，不仅对算法实现进行抽象，
 * 	对于调用者也进行抽象，让系统扩性更好，更灵活。世界没有免费的午餐，灵活性变高了，但是复杂性也变高了，对程序员的要求也更高。
 * */
public class BridgeTest {
    public static void main(String[] args) {
        Software software = new Camera();
        Phone phone = new HuaWei(software);
        phone.run();
        System.out.println("----------------");
        software = new Message();
        phone = new Iphone(software);
        phone.run();
    }
}