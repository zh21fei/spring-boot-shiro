package com.neo.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Scanner;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/8/20
 */
public class TestNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            StringBuilder sb = new StringBuilder();
            sb.append(sc.next());
            String str = sb.reverse().substring(0, sb.length() - 2);
            char ch[] = str.toCharArray();
            int sum = 0;
            for (int i = 0; i < ch.length; i++) {
                if (ch[i] >= 'A' && ch[i] <= 'F') {
                    sum += ((int) ch[i] - 55) * Math.pow(16, i);
                } else {
                    sum += ((int) ch[i] - 48) * Math.pow(16, i);
                }
            }
            System.out.println(sum);
        }
    }
}

