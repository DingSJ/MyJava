package com.china.designPattern.memento.test1;

public class Caretaker {
    private Memento memento;
    /**
     * 备忘录的取值方法
     * @return
     */
    public Memento retrieveMenento() {
        return this.memento;
    }
    /**
     * 备忘录的赋值方法
     * @param memento
     */
    public void saveMemento(Memento memento) {
        this.memento = memento;
    }
}