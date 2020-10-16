package com.neo.test;

import java.security.GeneralSecurityException;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/8/21
 */
public class SendEmail2 {
    public static void main(String[] args) throws GeneralSecurityException {
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i ; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("==========");
        for (int i = 5; i > 0; i--) {
            for (int j = 5; j > i ; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j < i; j++) {
                System.out.print("*");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

