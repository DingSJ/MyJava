package com.china.designPattern.factory;

public class RedisBook extends Book {

    @Override
    String contents() {
        return "This is a Redis book";
    }
}
