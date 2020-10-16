package com.neo.lambda;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;


/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/23
 */

@SpringBootTest
public class Test {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(getLcm(a,b));
        }
    }

    // 最大公约数
    public static int getGcd(int a,int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        if (max % min != 0) {
            return getGcd(min,max % min);
        }else {
            return min;
        }
    }

    // 最小公倍数
    public static int getLcm(int a, int b) {
        return a*b/getGcd(a, b);
    }

    @org.junit.Test
    public void Test0() {
        ConsumerInterface<String> consumerInterface = (str) -> {
            System.out.println(str);
        };
        consumerInterface.comsumer("abc");
    }

    @org.junit.Test
    public void Test01() {
        hello("zhangsan", (str) -> {
            System.out.println("nihao" + str);
        });
    }

    public void hello(String str, Consumer<String> com) {
        com.accept(str);
    }

    @org.junit.Test
    public void Test02() {
        Comparator<Integer> comparator = (x, y) -> {
            return Integer.compare(x, y);
        };
        int compare = comparator.compare(6, 12);
        System.out.println(compare);
    }

    @org.junit.Test
    public void Test03() {
        consumer("zhangsan", (m) -> {
            System.out.println("你好" + m);
        });
    }

    public void consumer(String str, Consumer<String> consumer) {
        consumer.accept(str);
    }

    @org.junit.Test
    public void Test04() {
        List<Integer> numList = getNumList(1, () -> (int) (Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        ArrayList<Integer> list = new ArrayList<>(10);
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }
}
