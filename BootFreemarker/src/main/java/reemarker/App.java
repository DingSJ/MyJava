package reemarker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication application = new SpringApplication(App.class);
        application.run();
        System.out.println("======>> Start SUCC");
    }
}
