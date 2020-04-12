package com.china.designPattern.chain;

public class Main {
    public static void main(String[] args){
        StaffMember staffMember = new StaffMember();
        SectionChief sectionChief = new SectionChief();
        Director director = new Director();
        //set Handler
        staffMember.setNextHandler(sectionChief);
        sectionChief.setNextHandler(director);
 
        staffMember.dispose("小王",400);
        staffMember.dispose("小混混",800);
        staffMember.dispose("老李",1200);
 
        staffMember.dispose("小明",10000);
    }
}