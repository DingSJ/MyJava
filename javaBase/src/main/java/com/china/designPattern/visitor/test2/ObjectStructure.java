package com.china.designPattern.visitor.test2;

import java.util.ArrayList;

/**
 * 对象结构，是元素的集合，提供元素的访问入口。
 */
public class ObjectStructure {

    // 使用集合保存Element元素，示例没有考虑多线程的问题。
    private ArrayList<Element> elements = new ArrayList<>();

    /**
     * 访问者访问元素的入口
     *
     * @param visitor 访问者
     */
    public void accept(Visitor visitor) {
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            element.accept(visitor);
        }
    }

    /**
     * 把元素加入到集合
     *
     * @param element 待添加的元素
     */
    public void addElement(Element element) {
        elements.add(element);
    }

    /**
     * 把元素从集合中移除
     *
     * @param element 要移除的元素
     */
    public void removeElement(Element element) {
        elements.remove(element);
    }
}