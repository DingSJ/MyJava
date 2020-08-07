package com.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("bService")
public class BService {

    @Autowired
    private AService aService;

    public String service() {
        System.out.println("B_Service run .... AService");
        return "B_SERVICE";
    }
}
