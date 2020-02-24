package com.china.test.Class;

import java.io.*;

public class FileReadTest {
    public static void main(String[] args) {

        readFile();
    }

    private static void readFile() {
        String filePath = "E:\\ideaSpace\\MyJava\\javaBase\\target\\classes\\com\\china\\test\\Class\\CD.txt";
        String filePath2 = "E:\\ideaSpace\\MyJava\\javaBase\\target\\classes\\com\\china\\test\\Class\\CD3.txt";

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             FileOutputStream fileOutputStream = new FileOutputStream(filePath2);
             BufferedReader br=new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
             BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fileOutputStream,"GBK") {

        })) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line == null || line.length() < 2) {
                    continue;
                }
                line = line.substring(0, 2) + " : " + line;
                bw.write(line);
                bw.newLine();
            }
//            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
