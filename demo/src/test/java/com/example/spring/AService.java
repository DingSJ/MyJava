package com.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("aService")
public class AService {

    @Autowired
    private BService bService;

    public String service() {
        System.out.println("AService run .... ");
        return bService.service();
    }
}
