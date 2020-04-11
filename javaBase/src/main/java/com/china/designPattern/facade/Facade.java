package com.china.designPattern.facade;

/**
 * 外观模式（Facade）:
 * 外观模式：为子系统中的一组接口提供一个统一的入口。外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
 * Facade Pattern: Provide a unified interface to a set of interfaces in a subsystem.
 * Facade defines a higher-level interface that makes the subsystem easier to use.
 * ————————————————
 * 隐藏了系统的复杂性，并向客户端提供了一个可以访问系统的接口。
 * 这种类型的设计模式属于结构性模式。为子系统中的一组接口提供了一个统一的访问接口，
 * 这个接口使得子系统更容易被访问或者使用。
 * <p>
 * 外观模式是一种使用频率非常高的结构型设计模式，
 * 它通过引入一个外观角色来简化客户端与子系统之间的交互，
 * 为复杂的子系统调用提供一个统一的入口，
 * 降低子系统与客户端的耦合度，且客户端调用非常方便。
 * <p>
 * 门面角色：	外观模式的核心。它被客户角色调用，它熟悉子系统的功能。内部根据客户角色的需求预定了几种功能的组合。
 * 子系统角色:	实现了子系统的功能。它对客户角色和Facade时未知的。它内部可以有系统内的相互交互，也可以由供外界调用的接口。
 * 客户角色:	通过调用Facede来完成要实现的功能。
 * <p>
 * 使用场景
 * 为复杂的模块或子系统提供外界访问的模块；
 * 子系统相互独立；
 * 在层析结构中，可以使用外观模式定义系统的每一层的入口。
 */
class Facade {
    private SubSystemA obj1 = new SubSystemA();
    private SubSystemB obj2 = new SubSystemB();
    private SubSystemC obj3 = new SubSystemC();

    public void Method() {
        obj1.MethodA();
        obj2.MethodB();
        obj3.MethodC();
    }
}