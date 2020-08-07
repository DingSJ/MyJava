package com.example.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringIOC {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPConfig.class);

        CService cService = context.getBean("cService", CService.class);
        AService aService = context.getBean("aService", AService.class);
        System.out.println(aService.service());
    }
}
