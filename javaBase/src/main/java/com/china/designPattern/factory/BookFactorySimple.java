package com.china.designPattern.factory;

/**
 * 简单工厂模式 - 静态工厂方法(Static Factory Method)模式
 * 类创建型模式
 * <p>
 * 优点：
 * 首先程序实现了解耦和，大大降低的程序之间的耦合性，我们增加车间不会影响到其他的车间进度；
 * 提高了程序的扩展性，如果我们想要增加一个车间，那么只需要实现造车厂类，完成自己的职能就好；
 * 组装车间可以免除直接创建对象的责任，他不需要自己直接去创建，而是通过我们专门的工厂类来创建，他只需要告诉我们他需要什么东西；
 * <p>
 * 缺点：
 * 首先大家应该可以明显的发现，如果我们每增加一个产品，那相应的我们就要增加一个子工厂，这样额外的加大了我们的开发量；
 * 由于工厂类集中了所有实例的创建逻辑，违反了高类聚责任分配原则
 * 违反了开闭原则
 */
public class BookFactorySimple {

    public static Book getBook(String bookName) {
        Book book = null;
        if ("Java".equals(bookName)) {
            book = new JavaBook();
        } else if ("Redis".equals(bookName)) {
            book = new RedisBook();
        } else if ("Python".equals(bookName)) {
            book = new PythonBook();
        } else if ("Spring".equals(bookName)) {
            book = new SpringBook();
        } else {
            book = new Book() {
                @Override
                String contents() {
                    return "Sorry, Unknown book.";
                }
            };
        }
        return book;
    }
}
