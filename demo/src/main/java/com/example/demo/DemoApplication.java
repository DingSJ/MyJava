package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        long currTime = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            System.out.println("<--  "+ i +"  -->");
//        }
        long newTime = System.currentTimeMillis();
        System.out.println("---> > 本次消耗时间：" + (float)(newTime - currTime) / 1000 + "s");
        SpringApplication.run(DemoApplication.class, args);
    }
}
