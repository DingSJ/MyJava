package com.china.designPattern.factory;

/**
 * 抽象：书
 * |———— Java
 * |____ Python
 * |____ Redis
 * |____ Spring
 * */
abstract class Book {

    abstract String contents();
}
