package com.china.designPattern.template.test;

public abstract class DataExportTemplate {

    public final void run() {
        String data = doGetData();
        doDataExport(data);

    }

    protected abstract void doDataExport(String data);

    protected abstract String doGetData();

}
