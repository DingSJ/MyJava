package com.china.designPattern.factory;

/**
 * 抽象工厂
 * */
public interface IBookFactory {
    Book getBook(String bookName);
}
