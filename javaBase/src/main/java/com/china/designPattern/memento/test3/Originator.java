package com.china.designPattern.memento.test3;

import java.util.ArrayList;
import java.util.List;

public class Originator {
    private List<String> states;
    //检查点序号
    private int index;
    /**
     * 构造函数
     */
    public Originator() {
        this.states = new ArrayList<>();
        index = 0;
    }
    /**
     * 工厂方法，返回一个新的备忘录对象
     * @return
     */
    public Memento createMemento() {
        return new Memento(states, index);
    }
    /**
     * 将发起人恢复到备忘录对象记录的状态上。
     * @param memento
     */
    public void restoreMemento(Memento memento) {
        this.states = memento.getStates();
        this.index = memento.getIndex();
    }
    /**
     * 状态的赋值方法
     * @param state
     */
    public void setState(String state) {
        this.states.add(state);
        this.index++;
    }
    public List<String> getStates() {
        return states;
    }
    /**
     * 辅助方法，打印所有状态
     */
    public void pringStates() {
        System.out.println("当前检查点共有：" + states.size() + "个状态值");
        for (String state : states) {
            System.out.println(state);
        }
    }
}