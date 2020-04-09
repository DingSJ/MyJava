package com.china.designPattern.prototype;

import com.china.optional.Person;

import java.util.Arrays;
import java.util.List;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {

        People person = new People();
        person.setName("ZhangSan");
        person.setAge(23);

        person.setDemos(Arrays.asList(new Demo("aaaa","aaaaaaaa"),new Demo("bbbb","bbbbbbb")));

        People friend = new People();
        friend.setName("Jack");
        friend.setAge(33);
        friend.setHobby(Arrays.asList("冒险"));

        person.setFriend(friend);
        person.setHobby(Arrays.asList("吃", "喝", "睡"));

        System.out.println("person: " + person);

        //
        // private People friend;
        //private List<String> hobby;
        //private List<Demo> demos;
        //

        // 对象 Clone
        People copyPerson = (People) person.clone();
        System.out.println("copyPerson：" + copyPerson);

        System.out.println("copyPerson == person: " + (person == copyPerson));


        People personFriend = person.getFriend();
        List<String> personHobby = person.getHobby();
        List<Demo> personDemos = person.getDemos();

        People copyFriend = copyPerson.getFriend();
        List<String> copyHobby = copyPerson.getHobby();
        List<Demo> copyDemos = copyPerson.getDemos();

        System.out.println("personFriend == copyFriend : " + (copyFriend == personFriend));
        System.out.println("personHobby == copyHobby: " + (personHobby == copyHobby));
        System.out.println("personDemos == copyDemos: " + (personDemos == copyDemos));

    }
}
