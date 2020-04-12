package com.china.designPattern.interpreter;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private final Map<String, Integer> valueMap = new HashMap<>(16);

    public void addValue(final String key, final int value) {
        valueMap.put(key, Integer.valueOf(value));
    }

    public Map<String, Integer> getValueMap() {
        return valueMap;
    }

    public int getValue(final String key) {
        return valueMap.get(key).intValue();
    }
}