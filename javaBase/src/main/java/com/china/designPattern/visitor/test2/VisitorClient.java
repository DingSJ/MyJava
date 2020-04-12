package com.china.designPattern.visitor.test2;

/**
 * 如果教师发表论文数超过8篇或者学生论文超过2篇可以评选科研优秀奖，
 * 如果教师教学反馈分大于等于85分或者学生成绩大于等于90分可以评选成绩优秀奖。
 */
public class VisitorClient {

    public static void main(String[] args) {
        // 初始化元素
        Element stu1 = new Student("Student Jim", 92, 3);
        Element stu2 = new Student("Student Ana", 89, 1);
        Element t1 = new Teacher("Teacher Mike", 83, 10);
        Element t2 = new Teacher("Teacher Lee", 88, 7);
        // 初始化对象结构
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.addElement(stu1);
        objectStructure.addElement(stu2);
        objectStructure.addElement(t1);
        objectStructure.addElement(t2);
        // 定义具体访问者，选拔成绩优秀者
        Visitor gradeSelection = new GradeSelection();
        // 具体的访问操作，打印输出访问结果
        objectStructure.accept(gradeSelection);
        System.out.println("----结构不变，操作易变----");
        // 数据结构是没有变化的，如果我们还想增加选拔科研优秀者的操作，那么如下。
        Visitor researcherSelection = new ResearcherSelection();
        objectStructure.accept(researcherSelection);
    }
}