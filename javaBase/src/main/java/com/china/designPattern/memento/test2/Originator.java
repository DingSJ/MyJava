package com.china.designPattern.memento.test2;

public class Originator {
    private String state;
    /**
     * 将发起人的状态恢复到备忘录对象所记录的状态
     * @param memento
     */
    public void restoreMemento(MementoIF memento) {
        this.state = ((Memento)memento).getState();
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
        System.out.println("当前状态：" + this.state);
    }

    /**
     * 工厂方法，返回一个新的备忘录对象
     * @return
     */
    public MementoIF createMemento() {
        return new Memento(this.state);
    }
    
    private class Memento implements MementoIF {
        private String state;
        /**
         * 构造方法
         * @param state
         */
        private Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}