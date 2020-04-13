package com.china.designPattern.observer.javaObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Library extends Observable{

    private List<Book> bookList;

    public Library() {
        // TODO Auto-generated constructor stub
        this.bookList = new ArrayList<>();
        //添加两本书
        Book android = new Book("Android","李江东");
        Book HongLou = new Book("红楼梦", "曹雪芹");
        this.bookList.add(android);
        this.bookList.add(HongLou);
    }

    public void addBook(Book book) {
        super.setChanged();
        this.bookList.add(book);
        super.notifyObservers(book);
    }

    public void delBook(Book book) {
        this.bookList.remove(book);
    }
}