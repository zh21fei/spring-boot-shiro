package com.neo.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Random;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/3
 */
public class Test {
    public static void main(String[] args) {

        String s0 = md5("000", "0@test.com8d78869f470951332959580424d4bf4f");
        String s1 = md5("111", "1@test.comtd7q8lf2v3t7wgo63iwidiylfyhffwn2");
        String s2 = md5("222", "2@test.comho2vbluroz9vi8p5vxhamnhk1rqwwc9u");
        String s3 = md5("333", "3@test.comod6reygzydv7om76auwj7r0396tjlce6");
        System.out.println(s0);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println(getRandomString());

        System.out.println("4tfiusy7dykt1uhz2eqgvfsfvd24xc7r".length());



    }

    public static final String md5(String password, String salt) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //密码
        Object source = password;
        //加密次数,比如加密两次，相当于 md5(md5(""));
        int hashIterations = 2;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        return result.toString();
    }

    public static String getRandomString() {
        //1.  定义一个字符串（a-z，0-9）即36个数字字母；
        String str = "zxcvbnmlkjhgfdsaqwertyuiop1234567890";
        //2.  由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //3.  长度为几就循环几次
        for (int i = 0; i < 32; ++i) {
            //从62个的数字或字母中选择
            int number = random.nextInt(36);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }
}
