package com.china.optional;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalTest {

    @Test // 1
    public void testOptioanal(){
        Optional<Person> person = Optional.empty();
        System.out.println(person.get());
    }
    @Test // 2
    public void testOptioanal2(){
        Optional<Person> personOptional = Optional.of(new Person("Zhang"));
        System.out.println(personOptional.get());
    }
    @Test // 3  java.lang.NullPointerException
    public void testOptioanal3(){
        Optional<Person> personOptional = Optional.of(null);
    }
    @Test // 4  personOptional: Optional.empty
    public void testOptioanal4(){
        Optional<Person> personOptional = Optional.ofNullable(null);
        System.out.println("personOptional1: " + personOptional);
        System.out.println("personOptional.isPresent: " + personOptional.isPresent());
        personOptional = Optional.ofNullable(new Person("Zhang"));
        System.out.println("personOptional2: " + personOptional);
        System.out.println("personOptional.isPresent: " + personOptional.isPresent());

        /*
                personOptional1: Optional.empty
                personOptional.isPresent: false
                personOptional2: Optional[Person{name='Zhang', car=null}]
                personOptional.isPresent: true
        */
    }
    @Test // 5
    public void testOptioanal5(){
        Optional<Person> personOptional = Optional.ofNullable(new Person("Zhang"));
        System.out.println("personOptional: " + personOptional);
        System.out.println("personOptional.get: " + personOptional.get());
        /*
                personOptional: Optional[Person{name='Zhang', car=null}]
                personOptional.get: Person{name='Zhang', car=null}

        */
    }
    @Test // 6
    public void testOptioanal6(){
        Optional<Person> personOptional = Optional.ofNullable(new Person("Zhang"));
        personOptional.ifPresent(person -> System.out.println(person));
        /*
                Person{name='Zhang', car=null}

        */
    }
    @Test // 7
    public void testOptioanal7(){
        Optional<Person> personOptional = Optional.ofNullable(new Person("Zhang"));
        personOptional.ifPresent(System.out::println);
        System.out.println(personOptional.isPresent());
        /*
                Person{name='Zhang', car=null}

        */
    }
    @Test // 8
    public void testMap(){
        Map map = new HashMap();
        map.remove("aa");
        System.out.println(map);
    }
}
