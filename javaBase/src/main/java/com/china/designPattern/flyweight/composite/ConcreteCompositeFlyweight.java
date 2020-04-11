package com.china.designPattern.flyweight.composite;

import com.china.designPattern.flyweight.Flyweight;

import java.util.HashMap;
import java.util.Map;

//复合享元角色
public class ConcreteCompositeFlyweight implements Flyweight {
 
    private Map<String, Flyweight> files = new HashMap<String, Flyweight>();
 
    /**
     * 增加一个新的单纯享元对象到聚集中
     */
    public void add(String intrinsicState, Flyweight fly) {
        files.put(intrinsicState, fly);
    }
 
    /**
     * 外蕴状态作为参数传入到方法中
     */
    @Override
    public void operation(String state) {
        Flyweight fly = null;
        for (String intrinsicState : files.keySet()) {
            fly = files.get(intrinsicState);
            fly.operation(state);
        }
 
    }
 
}
