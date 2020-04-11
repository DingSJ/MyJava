package com.china.designPattern.facade;


public class Computer {
    CPU cpu = new CPU();
    Memory memory = new Memory();
    Screen screen = new Screen();
 
    public void start() {
        cpu.start();
        memory.start();
        screen.start();
    }
 
}