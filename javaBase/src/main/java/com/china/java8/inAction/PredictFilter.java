package com.china.java8.inAction;

import java.util.ArrayList;
import java.util.List;

public class PredictFilter<T> {
    public static <T> List<T> filter(List<T> list, Predicate<T> predict) {
        List<T> tList = new ArrayList<>();
        for (T e : list) {
            if (predict.test(e)) {
                tList.add(e);
            }
        }
        return tList;
    }
}
