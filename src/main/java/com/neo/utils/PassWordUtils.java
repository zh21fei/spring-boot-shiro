package com.neo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Random;

/**
 * 密码加盐进行加密的方法
 *
 * @author zhangpengfei
 * @since 2020/6/4
 */
public class PassWordUtils {
    public static String md5(String password, String salt) {
        // 加密方式
        String hashAlgorithmName = "MD5";
        // 盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        // 密码
        Object source = password;
        // 加密次数,比如加密两次，相当于 md5(md5(""));
        int hashIterations = 2;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        return result.toString();
    }

    // 随机获取32位数字和字母的组合生成盐
    public static String getRandomString() {
        //1 定义一个字符串（a-z，0-9）即36个数字字母；
        String str = "zxcvbnmlkjhgfdsaqwertyuiop1234567890";
        // 由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        // 长度为几就循环几次
        for (int i = 0; i < str.length(); ++i) {
            //从36个的数字或字母中选择
            int number = random.nextInt(36);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
