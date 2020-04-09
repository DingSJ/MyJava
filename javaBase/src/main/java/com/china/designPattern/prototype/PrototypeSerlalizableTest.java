package com.china.designPattern.prototype;

import java.util.Arrays;
import java.util.List;

/**
 * clone 對象要实现 Serializable 接口
 *
 *  java.io.NotSerializableException: com.china.designPattern.prototype.PeopleSerializable
 * */
public class PrototypeSerlalizableTest {
    public static void main(String[] args) throws CloneNotSupportedException {

        PeopleSerializable person = new PeopleSerializable();
        person.setName("ZhangSan");
        person.setAge(23);

        person.setDemos(Arrays.asList(new Demo("aaaa","aaaaaaaa"),new Demo("bbbb","bbbbbbb")));

        PeopleSerializable friend = new PeopleSerializable();
        friend.setName("Jack");
        friend.setAge(33);
        friend.setHobby(Arrays.asList("冒险"));

        person.setFriend(friend);
        person.setHobby(Arrays.asList("吃", "喝", "睡"));

        System.out.println("person: " + person);

        // 引用类型
        //private People friend;
        //private List<String> hobby;
        //private List<Demo> demos;
        //

        // 对象 Clone
        PeopleSerializable copyPerson = person.clone();
        System.out.println("copyPerson：" + copyPerson);

        System.out.println("copyPerson == person: " + (person == copyPerson));


        PeopleSerializable personFriend = person.getFriend();
        List<String> personHobby = person.getHobby();
        List<Demo> personDemos = person.getDemos();

        PeopleSerializable copyFriend = copyPerson.getFriend();
        List<String> copyHobby = copyPerson.getHobby();
        List<Demo> copyDemos = copyPerson.getDemos();

        System.out.println("personFriend == copyFriend : " + (copyFriend == personFriend));
        System.out.println("personHobby == copyHobby: " + (personHobby == copyHobby));
        System.out.println("personDemos == copyDemos: " + (personDemos == copyDemos));

    }
}
