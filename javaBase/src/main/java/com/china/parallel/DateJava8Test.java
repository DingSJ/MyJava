package com.china.parallel;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class DateJava8Test {

    @Test
    public void testLocalDate(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        String localStr = "2020-03-25";
        LocalDate localDate1 = LocalDate.parse(localStr);
        System.out.println(localDate1);

        Date date = new Date();
        System.out.println(date);

        System.out.println(localDate1.compareTo(localDate));
    }
}
