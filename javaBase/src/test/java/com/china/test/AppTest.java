package com.china.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Unit test for simple ThreadBaseTest.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testLocalDate()
    {

        String date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date);
        String date1 = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(date1);
    }
}
