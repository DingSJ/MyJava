package com.china.java8;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 将数据、list 、文件转变成 Stream
 * */
public class StreamTest {

    @Test
    public void testArrStream(){
        Stream<String> arrStream = buildArrStream();
        System.out.println(arrStream);
        arrStream.forEach(System.out::println);
    }
    private Stream<String> buildArrStream() {
        String arr[] = {"a","b","c","d","e"};
        return Arrays.stream(arr);
    }

    @Test
    public void testFileStream(){
        Path path = Paths.get("E:\\ideaSpace\\MyJava\\javaBase\\src\\main\\java\\com\\china\\java8\\FilterTest.java");
        Stream<String> arrStream = null;
        try {
            arrStream = Files.lines(path, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrStream.forEach(System.out::println);

        System.out.println("==========================");
//        Path ps = Paths.get("E:\\ideaSpace\\MyJava\\javaBase\\src\\main\\java\\com\\china\\java8\\FunctionTest.java",
//                "E:\\ideaSpace\\MyJava\\javaBase\\src\\main\\java\\com\\china\\java8\\PredicteTest.java",
//                "E:\\ideaSpace\\MyJava\\javaBase\\src\\main\\java\\com\\china\\java8\\ThreadLocalTest.java");
//        try {
//            Stream<String> files = Files.lines(ps);
//            System.out.println("Count: " + files.count());
//            files.forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testListStream(){
        Stream<String> arrStream = buildListStream();
        System.out.println(arrStream);
        arrStream.forEach(System.out::println);
    }
    private Stream<String> buildListStream() {
        List<String> strList = Arrays.asList("aa","bb", "cc", "dd", "ee");
        return strList.stream();
    }

}
