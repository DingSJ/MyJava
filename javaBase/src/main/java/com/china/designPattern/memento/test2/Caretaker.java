package com.china.designPattern.memento.test2;

public class Caretaker {
    private MementoIF memento;
    /**
     * 备忘录的取值方法
     * @return
     */
    public MementoIF retrieveMenento() {
        return this.memento;
    }
    /**
     * 备忘录的赋值方法
     * @param memento
     */
    public void saveMemento(MementoIF memento) {
        this.memento = memento;
    }
}