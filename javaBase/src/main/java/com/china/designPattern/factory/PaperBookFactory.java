package com.china.designPattern.factory;

/**
 * 抽象工厂
 * */
public class PaperBookFactory implements IBookFactory {

    @Override
    public Book getBook(String bookName) {
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
