package zookeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringTest {
    public static void main(String[] args) {

        String str = new String("HELLO");
        String str2 = "HELLO";
        modify(str);
        System.out.println(str);
        System.out.println(str == str2);

        List<Object> objList = new ArrayList<>();
        objList.add(null);
        System.out.println(objList.size());

        System.out.println(objList.get(0));
        System.out.println(objList.contains(null));

        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Random().nextInt(10);
        }

        insertSort(arr);

        long res = fun(5);
        System.out.println("\nres : " + res);
    }

    private static long fun(long i) {
        if (i == 1){
            return 1;
        }
        return i*fun(i - 1);
    }

    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int preIndex = i - 1;
            int currValue = arr[i];
            while (preIndex >= 0 && currValue < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = currValue;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }

        String str = "Hello";
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }

    }

    private static String modify(String str) {
        str += "World";
        return str;
    }
}
