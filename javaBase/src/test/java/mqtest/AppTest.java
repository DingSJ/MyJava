package mqtest;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Unit mqtest for simple ThreadBaseTest.
 */
public class AppTest 
{

    @Test
    public void testLocalDate()
    {

        String date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date);
        String date1 = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(date1);

        System.out.println(JSON.toJSONString(System.getProperties()));

    }

    @Test
    public void testAmt(){
        String val = "12.30000";
        BigDecimal valDecimal = new BigDecimal(val);
        System.out.println(valDecimal.stripTrailingZeros());
    }
}
