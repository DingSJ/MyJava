package com.china.designPattern.bridge.softwareDemo;

public abstract class Phone {
    private Software software;

    public void setSoftware(Software software) {
        this.software = software;
    }

    public Software getSoftware() {
        return software;
    }

    public Phone(Software software) {
        this.software = software;
    }

    public abstract void run();
}