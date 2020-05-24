package mqtest.Class;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ClassTest {

    private static Object o;

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("mqtest.Class.ClassDemo");
            System.out.println(JSON.toJSONString(clazz));

            Annotation[] annotations = clazz.getAnnotations();

            ClassLoader classLoader = clazz.getClassLoader();
            System.out.println(classLoader);
            System.out.println(JSON.toJSONString(annotations));

            Constructor[] constructors = clazz.getConstructors();
            System.out.println(JSON.toJSONString(constructors));

            Field[] fields = clazz.getFields();
            System.out.println(JSON.toJSONString(fields));

            o = (ClassDemo)clazz.newInstance();
            ((ClassDemo) o).age = 100;
            ((ClassDemo) o).hello("sss");

            Class<ClassDemo> demoClass = (Class<ClassDemo>) Class.forName("mqtest.Class.ClassDemo");
            Field[] declaredFields = demoClass.getDeclaredFields();
            System.out.println(JSON.toJSONString(declaredFields));


            Method hello = demoClass.getMethod("hello", String.class);
            hello.invoke(demoClass.newInstance(), "sssss");


            Annotation annotation = demoClass.getAnnotation(TestAnno.class);
            System.out.println("A: " + annotation);

            String resources = "CD.txt";
            InputStream inputStream = ClassTest.class.getResourceAsStream(resources);
            System.out.println(inputStream.markSupported());
            inputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
